package eu.kanade.tachiyomi.network

import okhttp3.Call
import okhttp3.Response
import rx.Observable
import kotlin.reflect.KType
import kotlin.reflect.typeOf

fun Call.asObservable(): Observable<Response> = throw Exception("Stub!")

fun Call.asObservableSuccess(): Observable<Response> = throw Exception("Stub!")

/**
 * Useful method to parse/serialize JSON responses.
 * 
 * @param T A serializable data class.
 * @return The data class filled with the JSON response data.
 */
inline fun <reified T> Response.parseAs(): T {
    return internalParseAs(typeOf<T>(), this)
}

fun <T> internalParseAs(type: KType, response: Response): T = throw Exception("Stub!")

suspend fun Call.await(): Response = throw Exception("Stub!")
