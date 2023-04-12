package eu.kanade.tachiyomi.network.interceptor

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * An OkHttp interceptor that handles given url host's rate limiting.
 *
 * **Examples:**
 * ```
 * override val client = network.client.newBuilder()
 *     // 5 requests per second to api.videos.com
 *     .rateLimitHost(httpUrl = "api.videos.com".toHttpUrl(), permits = 5, period = 1, unit = TimeUnit.SECONDS)
 *     // 20 requests per second to previews.xyz
 *     .rateLimitHost(httpUrl = "previews.xyz".toHttpUrl(), permits = 20)
 *     // 10 requests per 2 minutes to cdn.thumbnails.org
 *     .rateLimitHost("cdn.thumbnails.org".toHttpUrl(), 10, 2, TimeUnit.MINUTES)
 *     .build()
 * ```
 *
 * @since extension-lib 1.3
 *
 * @param httpUrl {HttpUrl} The url host that this interceptor should handle. Will get url's host by using HttpUrl.host()
 * @param permits {Int}   Number of requests allowed within a period of units.
 * @param period {Long}   The limiting duration. Defaults to 1.
 * @param unit {TimeUnit} The unit of time for the period. Defaults to seconds.
 */
fun OkHttpClient.Builder.rateLimitHost(
    httpUrl: HttpUrl,
    permits: Int,
    period: Long = 1,
    unit: TimeUnit = TimeUnit.SECONDS,
): OkHttpClient.Builder = throw Exception("Stub!")
