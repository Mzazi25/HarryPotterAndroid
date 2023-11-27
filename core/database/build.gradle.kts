@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("mzazi.android.library")
    id("mzazi.android.hilt")
    id("mzazi.android.room")
    id("mzazi.spotless")
}

android {
    namespace = "com.mzazi.core.database"
    defaultConfig {
        // The schemas directory contains a schema file for each version of the Room database.
        // This is required to enable Room auto migrations.
        // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization)

}
