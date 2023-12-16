import java.net.URL
import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
    id("org.jetbrains.dokka")
}

val version = "14"

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    compileOnly("io.reactivex:rxjava:1.3.8")
    compileOnly("io.reactivex:rxandroid:1.2.1")
    compileOnly("org.jsoup:jsoup:1.16.1")
    compileOnly("com.github.inorichi.injekt:injekt-core:65b0440")

    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    val json = "1.5.1"
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:$json")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json-okio:$json")
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("extensions-lib")
            moduleVersion.set(version)
            outputDirectory.set(file("build/docs/"))
            // Speedup doc generation
            // offlineMode.set(true)
            includes.from("Module.md")

            perPackageOption {
                matchingRegex.set("android.content")
                suppress.set(true)
            }

            documentedVisibilities.set(listOf(
                Visibility.PUBLIC,
                Visibility.PROTECTED
            ))

            externalDocumentationLink {
                url.set(URL("https://square.github.io/okhttp/4.x/"))
                // https://github.com/square/okhttp/issues/7338
                packageListUrl.set(URL("https://colinwhite.me/okhttp3-package-list"))
            }

            externalDocumentationLink {
                url.set(URL("https://jsoup.org/apidocs/"))
                packageListUrl.set(URL("https://jsoup.org/apidocs/element-list"))
            }

            externalDocumentationLink {
                url.set(URL("https://reactivex.io/RxJava/1.x/javadoc/"))
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.aniyomiorg"
            artifactId = "extensions-lib"
            version = version

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
