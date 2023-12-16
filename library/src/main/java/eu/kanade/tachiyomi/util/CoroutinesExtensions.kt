package eu.kanade.tachiyomi.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

inline fun <A, B> Iterable<A>.parallelMap(crossinline f: suspend (A) -> B): List<B> =
    runBlocking {
        map { async(Dispatchers.Default) { f(it) } }.awaitAll()
    }

inline fun <A, B> Iterable<A>.parallelMapNotNull(crossinline f: suspend (A) -> B?): List<B> =
    runBlocking {
        map { async(Dispatchers.Default) { f(it) } }.awaitAll().filterNotNull()
    }

inline fun <A, B> Iterable<A>.parallelFlatMap(crossinline f: suspend (A) -> Iterable<B>): List<B> =
    runBlocking {
        map { async(Dispatchers.Default) { f(it) } }.awaitAll().flatten()
    }

inline fun <A, B> Iterable<A>.parallelCatchingFlatMap(crossinline f: suspend (A) -> Iterable<B>): List<B> =
    runBlocking {
        map {
            async(Dispatchers.Default) {
                runCatching { f(it) }.getOrElse { emptyList() }
            }
        }.awaitAll().flatten()
    }
