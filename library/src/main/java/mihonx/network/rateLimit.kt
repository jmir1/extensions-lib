@file:Suppress("UnusedReceiverParameter", "UNUSED", "UNUSED_PARAMETER")

package mihonx.network

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * An OkHttp interceptor that enforces rate limiting across all requests.
 *
 * Examples:
 *
 * permits = 5,  period = 1.seconds  =>  5 requests per second
 * permits = 10, period = 2.minutes  =>  10 requests per 2 minutes
 *
 * @since extension-lib 16
 *
 * @param permits [Int]     Number of requests allowed within a period of units.
 * @param period [Duration] The limiting duration. Defaults to 1.seconds.
 */
fun OkHttpClient.Builder.rateLimit(
    permits: Int,
    period: Duration = 1.seconds
): OkHttpClient.Builder = throw RuntimeException("Stub!")

/**
 * An OkHttp interceptor that handles given url host's rate limiting.
 *
 * Examples:
 *
 * url = "https://api.anime.example", permits = 5, period = 1.seconds =>  5 requests per second to any url starting with "https://api.anime.example"
 * url = "https://cdn.anime.example/image", permits = 10, period = 2.minutes  =>  10 requests per 2 minutes to any url starting with "https://cdn.anime.example/image"
 *
 * @since extension-lib 16
 *
 * @param url [String]      The url host that this interceptor should handle. Will get url's host by using HttpUrl.host()
 * @param permits [Int]     Number of requests allowed within a period of units.
 * @param period [Duration] The limiting duration. Defaults to 1.seconds.
 */
fun OkHttpClient.Builder.rateLimit(
    url: String,
    permits: Int,
    period: Duration = 1.seconds,
): OkHttpClient.Builder = throw RuntimeException("Stub!")

/**
 * An OkHttp interceptor that handles given url host's rate limiting.
 *
 * Examples:
 *
 * httpUrl = "https://api.anime.example".toHttpUrlOrNull(), permits = 5, period = 1.seconds =>  5 requests per second to any url starting with "https://api.anime.example"
 * httpUrl = "https://cdn.anime.example/image".toHttpUrlOrNull(), permits = 10, period = 2.minutes  =>  10 requests per 2 minutes to any url starting with "https://cdn.anime.example/image"
 *
 * @since extension-lib 16
 *
 * @param httpUrl [HttpUrl] The url host that this interceptor should handle. Will get url's host by using HttpUrl.host()
 * @param permits [Int]     Number of requests allowed within a period of units.
 * @param period [Duration] The limiting duration. Defaults to 1.seconds.
 */
fun OkHttpClient.Builder.rateLimit(
    httpUrl: HttpUrl,
    permits: Int,
    period: Duration = 1.seconds,
): OkHttpClient.Builder = throw RuntimeException("Stub!")

/**
 * An OkHttp interceptor that enforces conditional rate limiting based on a given condition.
 *
 * Examples:
 *
 * permits = 5, period = 1.seconds, shouldLimit = { it.host == "api.anime.example" } => 5 requests per second to api.anime.example.
 * permits = 10, period = 2.minutes, shouldLimit = { it.encodedPath.startsWith("/images/") } => 10 requests per 2 minutes to paths starting with "/images/".
 *
 * @since extension-lib 16
 *
 * @param permits [Int]     Number of requests allowed within a period of units.
 * @param period [Duration] The limiting duration. Defaults to 1.seconds.
 * @param shouldLimit       A predicate to determine whether the rate limit should apply to a given request.
 */
fun OkHttpClient.Builder.rateLimit(
    permits: Int,
    period: Duration = 1.seconds,
    shouldLimit: (HttpUrl) -> Boolean,
): OkHttpClient.Builder = throw RuntimeException("Stub!")
