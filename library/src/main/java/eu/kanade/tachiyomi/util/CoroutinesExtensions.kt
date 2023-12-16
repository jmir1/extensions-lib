package eu.kanade.tachiyomi.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

/**
 * Parallel implementation of [kotlin.collections.Iterable.map].
 *
 * @since extensions-lib 14
 */
inline fun <A, B> Iterable<A>.parallelMap(crossinline f: suspend (A) -> B): List<B> =
    runBlocking {
        map { async(Dispatchers.Default) { f(it) } }.awaitAll()
    }

/**
 * Parallel implementation of [kotlin.collections.Iterable.mapNotNull].
 *
 * @since extensions-lib 14
 */
inline fun <A, B> Iterable<A>.parallelMapNotNull(crossinline f: suspend (A) -> B?): List<B> =
    runBlocking {
        map { async(Dispatchers.Default) { f(it) } }.awaitAll().filterNotNull()
    }

/**
 * Parallel implementation of [kotlin.collections.Iterable.flatMap].
 *
 * @since extensions-lib 14
 */
inline fun <A, B> Iterable<A>.parallelFlatMap(crossinline f: suspend (A) -> Iterable<B>): List<B> =
    runBlocking {
        map { async(Dispatchers.Default) { f(it) } }.awaitAll().flatten()
    }

/**
 * Parallel implementation of [kotlin.collections.Iterable.flatMap], but running
 * the transformation function inside a [runCatching] block.
 *
 * @since extensions-lib 14
 */
inline fun <A, B> Iterable<A>.parallelCatchingFlatMap(crossinline f: suspend (A) -> Iterable<B>): List<B> =
    runBlocking {
        map {
            async(Dispatchers.Default) {
                runCatching { f(it) }.getOrElse { emptyList() }
            }
        }.awaitAll().flatten()
    }
