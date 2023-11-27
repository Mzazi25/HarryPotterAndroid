@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("mzazi.android.library")
    id("mzazi.android.hilt")
    id("mzazi.android.room")
    id("mzazi.spotless")
}

android {
    namespace = "com.mzazi.core.data"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:models"))
    implementation(project(":core:utils"))
    implementation(libs.timber)
}
