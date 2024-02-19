import dev.adamko.dokkatoo.dokka.parameters.VisibilityModifier

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
    alias(libs.plugins.dokkatoo)
}

val ver = "14"
version = ver
group = "com.github.aniyomiorg"

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

dokkatoo {
    moduleName.set("extensions-lib")
    moduleVersion.set(ver)
    dokkatooPublicationDirectory.set(layout.buildDirectory.dir("docs"))
    dokkatooSourceSets.main {
        // // Speedup doc generation
        // // offlineMode.set(true)
        includes.from("Module.md")

        perPackageOption {
            matchingRegex.set("android.content")
            suppress.set(true)
        }

        documentedVisibilities(VisibilityModifier.PUBLIC, VisibilityModifier.PROTECTED)

        externalDocumentationLinks {
            create("okhttp5") {
                url("https://square.github.io/okhttp/5.x/")
            }

            create("jsoup") {
                url("https://jsoup.org/apidocs/")
                packageListUrl("https://jsoup.org/apidocs/element-list")
            }

            create("rxjava") {
                url("https://reactivex.io/RxJava/1.x/javadoc/")
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "extensions-lib"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
