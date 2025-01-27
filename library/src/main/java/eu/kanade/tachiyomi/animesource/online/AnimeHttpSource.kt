@file:Suppress("UNUSED", "UNUSED_PARAMETER", "UnusedReceiverParameter", "DEPRECATION")

package eu.kanade.tachiyomi.animesource.online

import aniyomix.source.model.Hoster
import eu.kanade.tachiyomi.network.NetworkHelper
import eu.kanade.tachiyomi.animesource.AnimeCatalogueSource
import eu.kanade.tachiyomi.animesource.model.AnimeFilterList
import eu.kanade.tachiyomi.animesource.model.AnimesPage
import eu.kanade.tachiyomi.animesource.model.SAnime
import eu.kanade.tachiyomi.animesource.model.SEpisode
import eu.kanade.tachiyomi.animesource.model.Video
import mihonx.source.model.UserAgentType
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * A simple implementation for sources from a website.
 * Usually requires the usage of json serialization or similar techniques.
 */
abstract class AnimeHttpSource : AnimeCatalogueSource {

    /**
     * Base url of the website without the trailing slash, like: http://mysite.com
     */
    abstract val baseUrl: String

    /**
     * Version id used to generate the source id. If the site completely changes and urls are
     * incompatible, you may increase this value and it'll be considered as a new source.
     */
    open val versionId: Int = throw RuntimeException("Stub!")

    /**
     * Id of the source. By default it uses a generated id using the first 16 characters (64 bits)
     * of the MD5 of the string: sourcename/language/versionId
     * Note the generated id sets the sign bit to 0.
     */
    override val id: Long = throw RuntimeException("Stub!")

    /**
     * Network service.
     */
    protected val network: NetworkHelper = throw RuntimeException("Stub!")

    /**
     * Headers used for requests.
     */
    val headers: Headers = throw RuntimeException("Stub!")

    /**
     * Default network client for doing requests.
     */
    open val client: OkHttpClient = throw RuntimeException("Stub!")

    /**
     * Type of UserAgent a source needs
     */
    protected open val supportedUserAgentType: UserAgentType = UserAgentType.Universal

    /**
     * @since extensions-lib 16
     */
    protected fun getUserAgent(): String = throw RuntimeException("Stub!")

    /**
     * Headers builder for requests. Implementations can override this method for custom headers.
     */
     protected open fun headersBuilder(): Headers.Builder {
         throw RuntimeException("Stub!")
     }

    override suspend fun getDefaultAnimeList(page: Int): AnimesPage = throw RuntimeException("Stub!")

    override suspend fun getLatestAnimeList(page: Int): AnimesPage = throw RuntimeException("Stub!")

    override suspend fun getAnimeList(
        query: String,
        filters: AnimeFilterList,
        page: Int
    ): AnimesPage = throw RuntimeException("Stub!")

    override suspend fun getAnimeDetails(
        anime: SAnime,
        updateAnime: Boolean,
        fetchEpisodes: Boolean
    ): Pair<SAnime, List<SEpisode>> = throw RuntimeException("Stub!")

    override suspend fun getHosterList(episode: SEpisode): List<Hoster> = throw RuntimeException("Stub!")

    override suspend fun getVideoList(hoster: Hoster): List<Video> = throw RuntimeException("Stub!")

    /**
     * Returns the resolved video of the episode link. Override only if it's needed to resolve
     * the video.
     *
     * @since extensions-lib 16
     *
     * @param video the video information
     * @return the resolved video, or null on failure
     */
    open suspend fun resolveVideo(video: Video): Video? = throw RuntimeException("Stub!")

    /**
     * Sorts the hoster list. Override this according to the user's preference.
     *
     * @since extensions-lib 16
     */
    protected open fun List<Hoster>.sortHosters(): List<Hoster> = throw RuntimeException("Stub!")

    /**
     * Sorts the video list. Override this according to the user's preference.
     *
     * @since extensions-lib 16
     */
    protected open fun List<Video>.sortVideos(): List<Video> = throw RuntimeException("Stub!")

    /**
     * Assigns the url of the episode without the scheme and domain. It saves some redundancy from
     * database and the urls could still work after a domain change.
     *
     * @param url the full url to the episode.
     */
    fun SEpisode.setUrlWithoutDomain(url: String) {
        throw RuntimeException("Stub!")
    }

    /**
     * Assigns the url of the anime without the scheme and domain. It saves some redundancy from
     * database and the urls could still work after a domain change.
     *
     * @param url the full url to the anime.
     */
    fun SAnime.setUrlWithoutDomain(url: String) {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the url of the provided anime. Useful to fix "open in webview" 
     * without overriding [getAnimeDetails].
     *
     * @since extensions-lib 14
     * @param anime the anime
     * @return url of the anime
     */
    open fun getAnimeUrl(anime: SAnime): String {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the url of the provided episode.
     *
     * @since extensions-lib 14
     * @param episode the episode
     * @return url of the episode
     */
    open fun getEpisodeUrl(episode: SEpisode): String {
        throw RuntimeException("Stub!")
    }

    override fun toString(): String {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the request for the popular anime given the page.
     *
     * @param page the page number to retrieve.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getDefaultAnimeList]")
    open fun popularAnimeRequest(page: Int): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response from the site and returns a [AnimesPage] object.
     *
     * @param response the response from the site.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getDefaultAnimeList]")
    open fun popularAnimeParse(response: Response): AnimesPage = throw RuntimeException("Stub!")

    /**
     * Returns the request for latest anime given the page.
     *
     * @param page the page number to retrieve.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getLatestAnimeList]")
    open fun latestUpdatesRequest(page: Int): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response from the site and returns a [AnimesPage] object.
     *
     * @param response the response from the site.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getLatestAnimeList]")
    open fun latestUpdatesParse(response: Response): AnimesPage = throw RuntimeException("Stub!")

    /**
     * Returns the request for the search anime given the page and filters.
     *
     * @param page the page number to retrieve.
     * @param query the search query.
     * @param filters the list of filters to apply.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getAnimeList]")
    open fun searchAnimeRequest(page: Int, query: String, filters: AnimeFilterList): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response from the site and returns a [AnimesPage] object.
     *
     * @param response the response from the site.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getAnimeList]")
    open fun searchAnimeParse(response: Response): AnimesPage = throw RuntimeException("Stub!")

    /**
     * Returns the request for the details of a anime. Override only if it's needed to change the
     * url, send different headers or request method like POST.
     *
     * @param anime the anime to be updated.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getAnimeDetails]")
    open fun animeDetailsRequest(anime: SAnime): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response from the site and returns the details of a anime.
     *
     * @param response the response from the site.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getAnimeDetails]")
    open fun animeDetailsParse(response: Response): SAnime = throw RuntimeException("Stub!")

    /**
     * Returns the request for updating the episode list. Override only if it's needed to override
     * the url, send different headers or request method like POST.
     *
     * @param anime the anime to look for episodes.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getEpisodeList]")
    open fun episodeListRequest(anime: SAnime): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response from the site and returns a list of episodes.
     *
     * @param response the response from the site.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getEpisodeList]")
    open fun episodeListParse(response: Response): List<SEpisode> = throw RuntimeException("Stub!")

    /**
     * Returns the request for getting the video list. Override only if it's needed to override
     * the url, send different headers or request method like POST.
     *
     * @param episode the episode to look for videos.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getVideoList]")
    open fun videoListRequest(episode: SEpisode): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response from the site and returns a list of videos.
     *
     * @param response the response from the site.
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Directly implement inside [getVideoList]")
    open fun videoListParse(response: Response): List<Video> = throw RuntimeException("Stub!")

    /**
     * Returns the list of filters for the source.
     */
    @Deprecated(
        "Use the new suspend variant instead",
        replaceWith = ReplaceWith("getSearchFilters")
    )
    override fun getFilterList(): AnimeFilterList {
        throw RuntimeException("Stub!")
    }

    /**
     * Called before inserting a new episode into database. Use it if you need to override episode
     * fields, like the title or the episode number. Do not change anything to [anime].
     *
     * @param episode the episode to be added.
     * @param anime the anime of the episode.
     */
    @Deprecated("All these modification should be done when constructing the episode")
    open fun prepareNewEpisode(episode: SEpisode, anime: SAnime) {}
}
