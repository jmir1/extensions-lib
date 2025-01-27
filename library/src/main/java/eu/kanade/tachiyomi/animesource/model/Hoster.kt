@file:Suppress("UNUSED")

package eu.kanade.tachiyomi.animesource.model

/**
 * Data class for representing a hoster
 *
 * @since extensions-lib 16
 *
 * @property hosterUrl    url of hoster
 * @property hosterName   name of hoster
 * @property videoList    initial list of videos, if any
 * @property internalData data used by the extension. Useful for getting videos
 */
data class Hoster(
    val hosterUrl: String = "",
    val hosterName: String = "",
    val videoList: List<Video>? = null,
    val internalData: String = "",
) {
    /**
     * Used for sites that doesn't have the concept of hosters. To use this, return a list of a
     * single hoster, with `hosterName` set to `Hoster.NO_HOSTER_LIST` and a non-null video list.
     *
     * @since extensions-lib 16
     */
    companion object {
        const val NO_HOSTER_LIST = "no_hoster_list"
    }
}
