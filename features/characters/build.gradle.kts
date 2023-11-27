@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("mzazi.android.library")
    id("mzazi.spotless")
    id("mzazi.android.library.compose")
    id("mzazi.android.feature")
    id("mzazi.android.hilt")
}

android {
    namespace = "com.mzazi.features.characters"
}

dependencies {
    implementation(project(":core:models"))
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:utils"))
    //Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime)
}
