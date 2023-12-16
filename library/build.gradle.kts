import java.net.URL
import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
    alias(libs.plugins.dokka)
}

val version = "14"

android {
    compileSdk = 34
    namespace = "eu.kanade.tachiyomi.animeextensions"

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
        }
    }

    val javaVersion = JavaVersion.VERSION_1_8
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
}

dependencies {
    compileOnly(libs.okhttp)
    compileOnly(libs.jsoup)
    compileOnly(libs.rxjava)
    compileOnly(libs.rxandroid)
    compileOnly(libs.injekt.core)
    compileOnly(libs.coroutines)
    compileOnly(libs.kotlin.json)
    compileOnly(libs.kotlin.json.okio)
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
