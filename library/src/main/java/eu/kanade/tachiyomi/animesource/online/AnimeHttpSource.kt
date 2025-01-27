package eu.kanade.tachiyomi.animesource.online

import eu.kanade.tachiyomi.network.NetworkHelper
import eu.kanade.tachiyomi.animesource.AnimeCatalogueSource
import eu.kanade.tachiyomi.animesource.model.*
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import rx.Observable

/**
 * A simple implementation for sources from a website.
 * Usually requires the usage of json serialization or similar techniques.
 */
@Suppress("unused", "unused_parameter")
abstract class AnimeHttpSource : AnimeCatalogueSource {

    @Suppress("DEPRECATION")
    override val language: String get() = lang

    @Suppress("DEPRECATION")
    override val hasSearchFilters: Boolean get() = getFilterList().isNotEmpty()

    /**
     * Network service.
     */
    protected val network: NetworkHelper = throw Exception("Stub!")

    /**
     * Base url of the website without the trailing slash, like: http://mysite.com
     */
    abstract val baseUrl: String

    /**
     * Version id used to generate the source id. If the site completely changes and urls are
     * incompatible, you may increase this value and it'll be considered as a new source.
     */
    open val versionId: Int = throw Exception("Stub!")

    /**
     * ID of the source. By default it uses a generated id using the first 16 characters (64 bits)
     * of the MD5 of the string `"${name.lowercase()}/$lang/$versionId"`.
     *
     * The ID is generated by the [generateId] function, which can be reused if needed
     * to generate outdated IDs for cases where the source name or language needs to
     * be changed but migrations can be avoided.
     *
     * Note: the generated ID sets the sign bit to `0`.
     */
    override val id: Long = throw Exception("Stub!")

    /**
     * Headers used for requests. Result of [headersBuilder]
     */
    val headers: Headers = throw Exception("Stub!")

    /**
     * Default network client for doing requests. Implementations can override this property
     * for custom [OkHttpClient] instances.
     *
     * **Usage example:**
     * ```
     * import okhttp3.Dns
     * .....
     * override val client: OkHttpClient = 
     *     network.client
     *         .newBuilder()
     *         .addInterceptor(RecaptchaDestroyer())
     *         .dns(Dns.SYSTEM)
     *         .build()
     * ```
     */
    open val client: OkHttpClient = throw Exception("Stub!")

    override suspend fun getSearchFilters(): AnimeFilterList {
        @Suppress("DEPRECATION")
        return getFilterList()
    }

    /**
     * Generates a unique ID for the source based on the provided [name], [lang] and
     * [versionId]. It will use the first 16 characters (64 bits) of the MD5 of the string
     * `"${name.lowercase()}/$lang/$versionId"`.
     *
     * Note: the generated ID sets the sign bit to `0`.
     *
     * Can be used to generate outdated IDs, such as when the source name or language
     * needs to be changed but migrations can be avoided.
     *
     * @since extensions-lib 14
     * @param name [String] the name of the source
     * @param lang [String] the language of the source
     * @param versionId [Int] the version ID of the source
     * @return a unique ID for the source
     */
    protected fun generateId(name: String, lang: String, versionId: Int): Long {
        throw Exception("Stub!")
    }

    /**
     * Headers builder for requests. Implementations can override this method for custom headers.
     *
     * **Usage examples:**
     * ```
     * // Adds headers to the default [Headers.Builder] instance, retaining
     * // headers like the default(or user-made) User-Agent.
     * override fun headersBuilder() = super.headersBuilder().add("Referer", baseUrl)
     * ```
     * ```
     * // Creates a new, empty [Headers.Builder] instance and adds a single header.
     * override fun headersBuilder() = Headers.Builder().add("Referer", baseUrl)
     * ```
     */
    protected open fun headersBuilder(): Headers.Builder {
        throw Exception("Stub!")
    }

    /**
     * Visible name of the source.
     */
    override fun toString(): String {
        throw Exception("Stub!")
    }

    /**
     * Returns an observable containing a page with a list of anime. Normally it's not needed to
     * override this method.
     *
     * @param page the page number to retrieve.
     */
    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getPopularAnime"),
    )
    override fun fetchPopularAnime(page: Int): Observable<AnimesPage> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for the popular anime given the page.
     *
     * @param page the page number to retrieve.
     */
    protected abstract fun popularAnimeRequest(page: Int): Request

    /**
     * Parses the response from the site and returns a [AnimesPage] object.
     *
     * @param response the response from the site.
     */
    protected abstract fun popularAnimeParse(response: Response): AnimesPage

    /**
     * Returns an observable containing a page with a list of anime. Normally it's not needed to
     * override this method, but can be useful to change the usual workflow and use functions with
     * different signatures from [searchAnimeRequest] or [searchAnimeParse].
     *
     * @param page the page number to retrieve.
     * @param query the search query.
     * @param filters the list of filters to apply.
     */
    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getSearchAnime"),
    )
    override fun fetchSearchAnime(page: Int, query: String, filters: AnimeFilterList): Observable<AnimesPage> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for the search anime given the page and filters.
     *
     * @param page the page number to retrieve.
     * @param query the search query.
     * @param filters the list of filters to apply.
     */
    protected abstract fun searchAnimeRequest(page: Int, query: String, filters: AnimeFilterList): Request

    /**
     * Parses the response from the site and returns a [AnimesPage] object.
     *
     * @param response the response from the site.
     */
    protected abstract fun searchAnimeParse(response: Response): AnimesPage

    /**
     * Returns an observable containing a page with a list of latest anime updates.
     *
     * @param page the page number to retrieve.
     */
    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getLatestUpdates"),
    )
    override fun fetchLatestUpdates(page: Int): Observable<AnimesPage> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for latest anime given the page.
     *
     * @param page the page number to retrieve.
     */
    protected abstract fun latestUpdatesRequest(page: Int): Request

    /**
     * Parses the response from the site and returns a [AnimesPage] object.
     *
     * @param response the response from the site.
     */
    protected abstract fun latestUpdatesParse(response: Response): AnimesPage

    /**
     * Get the updated details for a anime.
     * Normally it's not needed to override this method.
     *
     * @param anime the anime to be updated.
     * @return the updated anime.
     */
    override suspend fun getAnimeDetails(anime: SAnime): SAnime {
        throw Exception("Stub!")
    }

    @Deprecated("Use the non-RxJava API instead", replaceWith = ReplaceWith("getAnimeDetails"))
    override fun fetchAnimeDetails(anime: SAnime): Observable<SAnime> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for the details of a anime. Override only if it's needed to change the
     * url, send different headers or request method like POST.
     *
     * @param anime the anime to be updated.
     */
    open fun animeDetailsRequest(anime: SAnime): Request {
        throw Exception("Stub!")
    }

    /**
     * Parses the response from the site and returns the details of a anime.
     *
     * @param response the response from the site.
     */
    protected abstract fun animeDetailsParse(response: Response): SAnime

    /**
     * Get all the available episodes for an anime.
     * Normally it's not needed to override this method.
     *
     * @param anime the anime to update.
     * @return the episodes for the anime.
     * @throws LicensedEntryItemsException if a anime is licensed and therefore no episodes are available.
     */
    override suspend fun getEpisodeList(anime: SAnime): List<SEpisode> {
        throw Exception("Stub!")
    }

    @Deprecated("Use the non-RxJava API instead", replaceWith = ReplaceWith("getEpisodeList"))
    override fun fetchEpisodeList(anime: SAnime): Observable<List<SEpisode>> {
        throw Exception("Stub!")
    }

    /**
     * Get the list of videos a episode has. Videos should be returned
     * in the expected order; the index is ignored.
     *
     * @param episode the episode.
     * @return the videos for the episode.
     */
    override suspend fun getVideoList(episode: SEpisode): List<Video> {
        throw Exception("Stub!")
    }

    @Deprecated("Use the non-RxJava API instead", replaceWith = ReplaceWith("getVideoList"))
    override fun fetchVideoList(episode: SEpisode): Observable<List<Video>> {
        throw Exception("Stub!")
    }

    open fun fetchVideoUrl(video: Video): Observable<String> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for updating the episode list. Override only if it's needed to override
     * the url, send different headers or request method like POST.
     *
     * @param anime the anime to look for episodes.
     */
    protected open fun episodeListRequest(anime: SAnime): Request {
        throw Exception("Stub!")
    }

    /**
     * Parses the response from the site and returns a list of episodes.
     *
     * @param response the response from the site.
     */
    protected abstract fun episodeListParse(response: Response): List<SEpisode>

    /**
     * Returns the request for getting the video list. Override only if it's needed to override
     * the url, send different headers or request method like POST.
     *
     * @param episode the episode to look for videos.
     */
    protected open fun videoListRequest(episode: SEpisode): Request {
        throw Exception("Stub!")
    }

    /**
     * Parses the response from the site and returns a list of videos.
     *
     * @param response the response from the site.
     */
    protected open fun videoListParse(response: Response): List<Video> {
        throw Exception("Stub!")
    }

    /**
     * Sorts the video list.
     * Override this according to the user's preference.
     *
     * **Usage examples:**
     * ```
     * // Sorts by quality
     * override fun List<Video>.sort(): List<Video> {
     *     val quality = preferences.getString(PREF_QUALITY_KEY, PREF_QUALITY_DEFAULT)!!
     *     return sortedWith(
     *         compareBy { it.quality.contains(quality) }
     *     ).reversed()
     * }
     * ```
     * ```
     * // Sorts by quality and hardsub language
     * override fun List<Video>.sort(): List<Video> {
     *    val quality = preferences.getString(PREF_QUALITY_KEY, PREF_QUALITY_DEFAULT)!!
     *    val lang = preferences.getString(PREF_LANG_KEY, PREF_LANG_DEFAULT)!!
     *    return sortedWith(
     *        compareBy(
     *            { it.quality.contains(quality) },
     *            { it.quality.contains(lang) },
     *        ),
     *    ).reversed()
     * }
     * ```
     */
    protected open fun List<Video>.sort(): List<Video> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for getting the url to the source video. Override only if it's needed to
     * override the url, send different headers or request method like POST.
     *
     * @param video the video whose its links have to be fetched.
     */
    protected open fun videoUrlRequest(video: Video): Request {
        throw Exception("Stub!")
    }

    protected open fun videoUrlParse(response: Response): String {
        throw Exception("Stub!")
    }

    /**
     * Assigns the url of the episode without the scheme and domain. It saves some redundancy from
     * database and the urls could still work after a domain change.
     *
     * @param url the full url to the episode.
     */
    fun SEpisode.setUrlWithoutDomain(url: String) {
        throw Exception("Stub!")
    }

    /**
     * Assigns the url of the anime without the scheme and domain. It saves some redundancy from
     * database and the urls could still work after a domain change.
     *
     * @param url the full url to the anime.
     */
    fun SAnime.setUrlWithoutDomain(url: String) {
        throw Exception("Stub!")
    }

    /**
     * Returns the url of the given string without the scheme and domain.
     *
     * @param orig the full url.
     */
    private fun getUrlWithoutDomain(orig: String): String {
        throw Exception("Stub!")
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
        throw Exception("Stub!")
    }

    /**
     * Returns the url of the provided episode.
     *
     * @since extensions-lib 14
     * @param episode the episode
     * @return url of the episode
     */
    open fun getEpisodeUrl(episode: SEpisode): String {
        throw Exception("Stub!")
    }

    /**
     * Called before inserting a new episode into database. Use it if you need to override episode
     * fields, like the title or the episode number. Do not change anything to [anime].
     *
     * @param episode the episode to be added.
     * @param anime the anime of the episode.
     */
    open fun prepareNewEpisode(episode: SEpisode, anime: SAnime) {}

    /**
     * Returns the list of filters for the source.
     */
    @Deprecated(
        "Use the new suspend variant instead",
        replaceWith = ReplaceWith("getSearchFilters")
    )
    override fun getFilterList(): AnimeFilterList {
        throw Exception("Stub!")
    }
}
