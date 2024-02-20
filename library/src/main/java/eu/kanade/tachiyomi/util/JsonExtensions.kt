package eu.kanade.tachiyomi.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.okio.decodeFromBufferedSource
import kotlinx.serialization.serializer
import okhttp3.Response
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

/**
 * Parse and serialize the transformed response body as the type <T>.
 *
 * @param transform transformer function that does required changes to the response body.
 * @since extensions-lib 14
 */
inline fun <reified T> Response.parseAs(transform: (String) -> String): T {
    val responseBody = transform(body.string())
    return Injekt.get<Json>().decodeFromString(responseBody)
}

/**
 * Parse and serialize the response body as the type <T>.
 *
 * This function uses okio utilities instead of converting the response body to
 * a String, so you may have a small performance gain over `Response.parseAs(transform)`,
 * mainly in large responses.
 *
 * @since extensions-lib 14
 */
@ExperimentalSerializationApi
inline fun <reified T> Response.parseAs(): T = body.source().use {
    Injekt.get<Json>().decodeFromBufferedSource(serializer(), it)
}

/**
 * Parses and serializes the transformed String as the type <T>.
 *
 * @param transform transformer function that does required changes to the String.
 * @since extensions-lib 14
 */
inline fun <reified T> String.parseAs(transform: (String) -> String): T =
    Injekt.get<Json>().decodeFromString(transform(this))

/**
 * Parses and serializes the Json String as the type <T>.
 *
 * @since extensions-lib 14
 */
inline fun <reified T> String.parseAs(): T = Injekt.get<Json>().decodeFromString(this)
