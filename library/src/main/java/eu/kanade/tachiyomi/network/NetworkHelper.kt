@file:Suppress("UNUSED_PARAMETER", "UNUSED")

package eu.kanade.tachiyomi.network

import android.content.Context
import okhttp3.OkHttpClient

@Suppress("unused_parameter")
class NetworkHelper(context: Context) {

    val client: OkHttpClient = throw RuntimeException("Stub!")

    /**
     * @deprecated Since extension-lib 14
     */
    @Deprecated("The regular client handles Cloudflare by default", ReplaceWith("client"))
    val cloudflareClient: OkHttpClient = throw RuntimeException("Stub!")
}
