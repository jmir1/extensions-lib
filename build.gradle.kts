// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    kotlin("android") version "1.7.10" apply false
    id("org.jetbrains.dokka") version "1.7.20" apply false
    id("com.android.library") version "7.1.3" apply false
}


tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
