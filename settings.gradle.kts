pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven(url = "https://maven.aliyun.com/nexus/content/repositories/releases/")
        maven(url = "https://central.maven.org/maven2/")
        maven(url = "https://dl.bintray.com/laobie/maven")
        maven(url = "https://maven.google.com")
        maven(url = "https://jitpack.io")
        maven(url = "https://plugins.gradle.org/m2/")
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven(url = "https://maven.aliyun.com/nexus/content/repositories/releases/")
        maven(url = "https://central.maven.org/maven2/")
        maven(url = "https://dl.bintray.com/laobie/maven")
        maven(url = "https://maven.google.com")
        maven(url = "https://jitpack.io")
        maven(url = "https://plugins.gradle.org/m2/")
        gradlePluginPortal()
    }
}

rootProject.name = "demos"
include(":app")
