plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization").version("1.7.10")
    id("com.android.library")
    id("com.google.devtools.ksp") version "1.7.10-1.0.6"
    id("kotlin-parcelize")
}

val ktorVersion = "2.2.2"
val ktorfitVersion = "1.0.0-beta16"

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val kotlinxCoroutinesVersion = "1.6.4"
        val kotlinxDateTimeVersion = "0.4.0"
        val decomposeVersion = "1.0.0-alpha-06"
        val koinVersion = "3.2.1"

        val commonMain by getting {
            dependencies {
                // Kotlin
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinxDateTimeVersion")

                // Architecture
                implementation("com.arkivanov.decompose:decompose:$decomposeVersion")

                // DI
                implementation("io.insert-koin:koin-core:$koinVersion")

                // Network
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-resources:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-auth:$ktorVersion")

                implementation("de.jensklingenberg.ktorfit:ktorfit-lib:$ktorfitVersion")

                // Logging
                implementation ("org.slf4j:slf4j-simple:1.6.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinxCoroutinesVersion")
                implementation("androidx.core:core:1.9.0")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.kmm.network_sample"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}

dependencies {
    add("kspCommonMainMetadata", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspAndroid", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspIosX64", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspIosSimulatorArm64", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
}
