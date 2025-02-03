package eu.kanade.tachiyomi.animesource.utils

import android.content.SharedPreferences
import eu.kanade.tachiyomi.animesource.AnimeSource

/**
 * Gets instance of [SharedPreferences] scoped to the specific source.
 *
 * @since extensions-lib 16
 */
fun AnimeSource.sourcePreferences(): SharedPreferences = throw Exception("Stub!")

/**
 * Gets instance of [SharedPreferences] scoped to the specific source id.
 *
 * @since extensions-lib 16
 *
 * @param id source id which the [SharedPreferences] is scoped to.
 */
fun sourcePreferences(id: Long): SharedPreferences = throw Exception("Stub!")
