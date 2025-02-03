package eu.kanade.tachiyomi.util

import eu.kanade.tachiyomi.animesource.online.AnimeHttpSource
import eu.kanade.tachiyomi.network.GET
import eu.kanade.tachiyomi.network.POST
import eu.kanade.tachiyomi.network.awaitSuccess
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Response

/**
 * Send a GET request
 *
 * @since extensions-lib 16
 *
 * @param url the url
 * @param headers the headers, defaults to the headers generated by
 *                AnimeHttpSource.headersBuilder()
 */
context(AnimeHttpSource)
suspend inline fun OkHttpClient.get(
    url: String,
    headers: Headers = this@AnimeHttpSource.headers,
): Response {
    return newCall(GET(url, headers)).awaitSuccess()
}

/**
 * Send a GET request
 *
 * @since extensions-lib 16
 *
 * @param url the url
 * @param headers the headers, defaults to the headers generated by
 *                AnimeHttpSource.headersBuilder()
 */
context(AnimeHttpSource)
suspend inline fun OkHttpClient.get(
    url: HttpUrl,
    headers: Headers = this@AnimeHttpSource.headers,
): Response {
    return newCall(GET(url, headers)).awaitSuccess()
}

/**
 * Send a POST request
 *
 * @since extensions-lib 16
 *
 * @param url the url
 * @param headers the headers, defaults to the headers generated by
 *                AnimeHttpSource.headersBuilder()
 */
context(AnimeHttpSource)
suspend inline fun OkHttpClient.post(
    url: String,
    body: RequestBody,
    headers: Headers = this@AnimeHttpSource.headers,
): Response {
    return newCall(POST(url, headers, body)).awaitSuccess()
}
