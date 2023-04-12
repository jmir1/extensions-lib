package eu.kanade.tachiyomi.animesource

import androidx.preference.PreferenceScreen

/**
 * A interface to add user preferences to the source.
 *
 * The usual implementation looks like this:
 * ```
 * import android.app.Application
 * import android.content.SharedPreferences
 * import androidx.preference.PreferenceScreen
 * import eu.kanade.tachiyomi.animesource.ConfigurableAnimeSource
 * import eu.kanade.tachiyomi.animesource.online.AnimeHttpSource
 * import uy.kohesive.injekt.Injekt
 * import uy.kohesive.injekt.api.get
 * // some other imports...
 *
 * class SomeSource : ConfigurableAnimeSource, AnimeHttpSource() {
 *     // some code...
 *
 *     private val preferences: SharedPreferences by lazy {
 *         Injekt.get<Application>().getSharedPreferences("source_$id", 0x0000)
 *     }
 *
 *     override fun setupPreferenceScreen(screen: PreferenceScreen) {
 *         // some preferences...
 *     }
 * }
 * ```
 */
interface ConfigurableAnimeSource {

    /**
     * Implementations must override this method to add the user preferences.
     * 
     * You can use some stubbed inheritors of [androidx.preference.Preference] here.
     * 
     * **Common usage example:**
     * ```
     * // ============================== Settings ==============================
     * override fun setupPreferenceScreen(screen: PreferenceScreen) {
     *     val videoQualityPref = ListPreference(screen.context).apply {
     *         key = PREF_QUALITY_KEY // String, like "pref_quality"
     *         title = PREF_QUALITY_TITLE // String, like "Preferred quality:" 
     *         entries = PREF_QUALITY_ENTRIES // Array<String>, like arrayOf("240p", "720p"...)
     *         // Another Array<String>. Can be different from the property above, as long it have the same size
     *         // and equivalent values per index.
     *         entryValues = PREF_QUALITY_VALUES 
     *         setDefaultValue(PREF_QUALITY_DEFAULT)
     *         summary = "%s"
     *         setOnPreferenceChangeListener { _, newValue ->
     *             val selected = newValue as String
     *             val index = findIndexOfValue(selected)
     *             val entry = entryValues[index] as String
     *             preferences.edit().putString(key, entry).commit()
     *         }
     *     }
     *     screen.addPreference(videoQualityPref)
     * }
     * ```
     */
    fun setupPreferenceScreen(screen: PreferenceScreen)

}
