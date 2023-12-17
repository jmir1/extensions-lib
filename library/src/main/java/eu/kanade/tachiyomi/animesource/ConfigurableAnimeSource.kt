package eu.kanade.tachiyomi.animesource

import android.content.SharedPreferences
import androidx.preference.PreferenceScreen

/**
 * A interface to add user preferences to the source.
 */
interface ConfigurableAnimeSource {

    /**
     * Gets instance of [SharedPreferences] scoped to the specific source.
     *
     * @since extensions-lib 14
     */
    fun getSourcePreferences(): SharedPreferences = throw Exception("stub!")

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
