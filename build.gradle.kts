// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    dependencies {
        classpath("io.github.didi.dokit:dokitx-plugin:3.7.1")
    }

    configurations.all {
        resolutionStrategy {
            cacheDynamicVersionsFor(0, "seconds")
            cacheChangingModulesFor(0, "seconds")
        }
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
//    alias(libs.plugins.dokit) apply false
}

allprojects {
    configurations.all {
        resolutionStrategy {
            cacheDynamicVersionsFor(0, "seconds")
            cacheChangingModulesFor(0, "seconds")
        }
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}