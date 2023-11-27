@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("mzazi.android.library")
    id("mzazi.android.hilt")

    id("mzazi.spotless")
    id("mzazi.android.library.compose")
}

android {
    namespace = "com.mzazi.core.domain"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:data"))
    implementation(project(":core:models"))
    implementation(project(":core:utils"))
    implementation(libs.timber)
    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit)
    testImplementation(libs.turbine)
    testImplementation(libs.mock.android)
    testImplementation(libs.mock.agent)
    testImplementation(libs.truth)
    testImplementation(libs.coroutines.test)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    testImplementation(libs.arch.core)
    testImplementation(libs.test.robolectric)
    testImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.compose.ui.test.junit)
}
