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

inline fun <A, B> Iterable<A>.parallelMapIndexed(crossinline f: suspend (index: Int, A) -> B): List<B> =
    runBlocking {
        mapIndexed { index, it -> async(Dispatchers.Default) { f(index, it) } }.awaitAll()
    }

inline fun <A, B> Iterable<A>.parallelMapIndexedNotNull(crossinline f: suspend (index: Int, A) -> B?): List<B> =
    runBlocking {
        mapIndexed { index, it -> async(Dispatchers.Default) { f(index, it) } }.awaitAll().filterNotNull()
    }

inline fun <A, B> Iterable<A>.parallelFlatMap(crossinline f: suspend (A) -> Iterable<B>): List<B> =
    runBlocking {
        map { async(Dispatchers.Default) { f(it) } }.awaitAll().flatten()
    }

inline fun <A, B> Iterable<A>.parallelFlatMapIndexed(crossinline f: suspend (index: Int, A) -> Iterable<B>): List<B> =
    runBlocking {
        mapIndexed { index, it -> async(Dispatchers.Default) { f(index, it) } }.awaitAll().flatten()
    }
