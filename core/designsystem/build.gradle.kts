@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("mzazi.android.library")
    id("mzazi.spotless")
    id("mzazi.android.library.compose")
}

android {
    namespace = "com.mzazi.core.designsystem"
}

dependencies {
    implementation(libs.coil)
    implementation(libs.core.ktx)
    //Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime)
}
