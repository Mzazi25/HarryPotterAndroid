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
}
