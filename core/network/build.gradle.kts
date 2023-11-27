plugins {
    id("mzazi.android.library")
    id("mzazi.android.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("mzazi.spotless")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.mzazi.core.network"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:utils"))

    implementation(libs.androidx.lifecycle.runtime.compose)
    // Data & Async
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.converter)

}
