buildscript {
    repositories {
      google()
      mavenCentral()
    }
}
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.mapsplatform.secrets.gradle.plugin) apply false
    alias(libs.plugins.org.jlleitschuh.gradle.ktlint)
    alias(libs.plugins.io.gitlab.arturbosch.detekt)
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization) apply false
}
true
