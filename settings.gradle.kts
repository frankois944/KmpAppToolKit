enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "KmpAppToolKit"

pluginManagement {
    includeBuild("convention-plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
}

include(":kmpAppToolKitLib")
include(":sample:composeApp")
