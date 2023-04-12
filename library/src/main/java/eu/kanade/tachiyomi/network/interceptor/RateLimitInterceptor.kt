package eu.kanade.tachiyomi.network.interceptor

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * An OkHttp interceptor that handles rate limiting.
 *
 * **Examples:**
 * ```
 * override val client = network.client.newBuilder()
 *     // 5 requests per second
 *     .rateLimit(permits = 5, period = 1, unit = TimeUnit.SECONDS)
 *     // 15 requests per minute
 *     .rateLimit(permits = 15, unit = TimeUnit.MINUTES)
 *     // 10 requests per 2 minutes
 *     .rateLimit(permits = 10, period = 2, unit = TimeUnit.MINUTES)
 *     .build()
 * ```
 *
 * @since extension-lib 1.3
 *
 * @param permits {Int}   Number of requests allowed within a period of units.
 * @param period {Long}   The limiting duration. Defaults to 1.
 * @param unit {TimeUnit} The unit of time for the period. Defaults to seconds.
 */
fun OkHttpClient.Builder.rateLimit(
    permits: Int,
    period: Long = 1,
    unit: TimeUnit = TimeUnit.SECONDS,
): OkHttpClient.Builder = throw Exception("Stub!")
