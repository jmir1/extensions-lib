package eu.kanade.tachiyomi.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

inline fun <A, B> Iterable<A>.parallelMap(crossinline f: suspend (A) -> B): List<B> =
    runBlocking {
        map { async(Dispatchers.Default) { f(it) } }.awaitAll()
    }
