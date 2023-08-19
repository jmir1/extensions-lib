package eu.kanade.tachiyomi.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.okio.decodeFromBufferedSource
import kotlinx.serialization.serializer
import okhttp3.Response
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

inline fun <reified T> Response.parseAs(transform: (String) -> String): T {
    val responseBody = use { transform(it.body.string()) }
    return Injekt.get<Json>().decodeFromString(responseBody)
}

@ExperimentalSerializationApi
inline fun <reified T> Response.parseAs(): T {
    return use { res ->
        res.body.source().use {
            Injekt.get<Json>().decodeFromBufferedSource(serializer(), it)
        }
    }
}
