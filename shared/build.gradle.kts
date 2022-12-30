plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization").version("1.7.20")
    id("com.android.library")
    id("kotlin-parcelize")
}

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
        val ktorVersion = "2.1.2"

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
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
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
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("androidx.core:core:1.9.0")
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
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
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