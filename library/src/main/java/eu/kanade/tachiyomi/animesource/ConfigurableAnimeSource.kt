package eu.kanade.tachiyomi.animesource

import androidx.preference.PreferenceScreen

/**
 * A interface to add user preferences to the source. 
 */
interface ConfigurableAnimeSource {

    /**
     * Implementations must override this method to add the preferences.
     * Example:
     * ```
     *  override fun setupPreferenceScreen(screen: PreferenceScreen) {
     *      val somePref = ListPreference(screen.context).apply {
     *          // some code
     *      }
     *      screen.addPreference(somePref)
     * }
     * ```
     */
    fun setupPreferenceScreen(screen: PreferenceScreen)

}
