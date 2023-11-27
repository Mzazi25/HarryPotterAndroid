plugins {
    id("mzazi.android.library")
    id("mzazi.spotless")
}

android {
    namespace = "com.mzazi.core.utils"
    buildFeatures {
        buildConfig = true
    }
}


dependencies {
    implementation(libs.retrofit)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.androidx.lifecycle.runtime.compose)
}
