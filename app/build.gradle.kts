plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.kmm.network_sample.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.kmm.network_sample.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    val kotlinxCoroutinesVersion = "1.6.4"
    val decomposeVersion = "1.0.0-alpha-06"
    val koinVersion = "3.2.1"
    val composeVersion = "1.2.1"
    val activityComposeVersion = "1.6.0"
    val accompanistVersion = "0.27.0"

    implementation(project(":shared"))

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinxCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    // Architecture
    implementation("com.arkivanov.decompose:decompose:$decomposeVersion")
    implementation("com.arkivanov.decompose:extensions-compose-jetpack:$decomposeVersion")

    // DI
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")

    // UI
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
}