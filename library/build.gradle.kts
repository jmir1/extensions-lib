import dev.adamko.dokkatoo.dokka.parameters.KotlinPlatform
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
        includes.from("Module.md")

        // Temporary workaround for https://github.com/Kotlin/dokka/issues/2876.
        analysisPlatform.set(KotlinPlatform.JVM)

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

        val packageRoot = projectDir.resolve("src/main/java/eu/kanade/tachiyomi/")
        sourceLink {
            localDirectory.set(packageRoot.resolve("util/JsonExtensions.kt"))
            remoteUrl("https://github.com/aniyomiorg/extensions-lib/tree/main/library/src/main/java/eu/kanade/tachiyomi/util/JsonExtensions.kt")
            remoteLineSuffix.set("#L")
        }

        sourceLink {
            localDirectory.set(packageRoot.resolve("util/CoroutinesExtensions.kt"))
            remoteUrl("https://github.com/aniyomiorg/extensions-lib/tree/main/library/src/main/java/eu/kanade/tachiyomi/util/CoroutinesExtensions.kt")
            remoteLineSuffix.set("#L")
        }

        sourceLink {
            localDirectory.set(packageRoot.resolve("animesource/"))
            remoteUrl("https://github.com/aniyomiorg/aniyomi/tree/master/source-api/src/commonMain/kotlin/eu/kanade/tachiyomi/animesource/")
            // The line number is wrong, so we're not going to highlight it.
            remoteLineSuffix.set("#")
        }

        sourceLink {
            localDirectory.set(packageRoot.resolve("network/"))
            remoteUrl("https://github.com/aniyomiorg/aniyomi/tree/master/core/src/main/java/eu/kanade/tachiyomi/network/")
            remoteLineSuffix.set("#") // Same as before.
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
