pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HarryPotterAndroid"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:network")
include(":core:designsystem")
include(":core:database")
include(":core:models")
include(":core:utils")
include(":features:characters")
