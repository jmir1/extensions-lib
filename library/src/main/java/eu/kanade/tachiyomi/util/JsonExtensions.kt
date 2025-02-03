package eu.kanade.tachiyomi.util

import kotlinx.serialization.json.Json
import okhttp3.Response

/**
 * App provided default [Json] instance. Configured as
 * ```
 * Json {
 *     ignoreUnknownKeys = true
 *     explicitNulls = false
 * }
 * ```
 *
 * @since extensions-lib 16
 */
val defaultJson: Json = throw Exception("Stub!")

/**
 * Decodes and deserializes the given response to the value of type [T]
 *
 * @since extensions-lib 16
 *
 * @param json the [Json] instance to use for decoding. Defaults to app provided [defaultJson]
 */
inline fun <reified T> Response.parseAs(json: Json = defaultJson): T {
    return body.string().parseAs(json)
}

/**
 * Decodes and deserializes the given response to the value of type [T], as well as
 * transforming the response body before decoding
 *
 * @since extensions-lib 16
 *
 * @param json the [Json] instance to use for decoding. Defaults to app provided [defaultJson]
 */
inline fun <reified T> Response.parseAs(json: Json = defaultJson, transform: (String) -> String): T {
    return transform(body.string()).parseAs(json)
}

/**
 * Decodes and deserializes the given JSON string to the value of type [T]
 *
 * @since extensions-lib 16
 *
 * @param json the [Json] instance to use for decoding. Defaults to app provided [defaultJson]
 */
inline fun <reified T> String.parseAs(json: Json = defaultJson): T {
    return json.decodeFromString(this)
}

/**
 * Decodes and deserializes the given JSON string to the value of type [T], as well as
 * transforming the string body before decoding
 *
 * @since extensions-lib 16
 *
 * @param json the [Json] instance to use for decoding. Defaults to app provided [defaultJson]
 */
inline fun <reified T> String.parseAs(json: Json = defaultJson, transform: (String) -> String): T {
    return json.decodeFromString(transform(this))
}
