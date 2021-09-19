package eu.kanade.tachiyomi.animesource.online

import eu.kanade.tachiyomi.network.NetworkHelper
import eu.kanade.tachiyomi.animesource.AnimeCatalogueSource
import eu.kanade.tachiyomi.animesource.model.*
import eu.kanade.tachiyomi.network.GET
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import rx.Observable

/**
 * A simple implementation for sources from a website.
 */
@Suppress("unused", "unused_parameter")
abstract class AnimeHttpSource : AnimeCatalogueSource {

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
     * Id of the source. By default it uses a generated id using the first 16 characters (64 bits)
     * of the MD5 of the string: sourcename/language/versionId
     * Note the generated id sets the sign bit to 0.
     */
    override val id: Long = throw Exception("Stub!")

    /**
     * Headers used for requests.
     */
    val headers: Headers = throw Exception("Stub!")

    /**
     * Headers used for playing the video file
     */
    val videoHeaders: Headers = throw Exception("Stub!")

    /**
     * Default network client for doing requests.
     */
    open val client: OkHttpClient = throw Exception("Stub!")

    /**
     * Headers builder for requests. Implementations can override this method for custom headers.
     */
    protected open fun headersBuilder(): Headers.Builder {
        throw Exception("Stub!")
    }

    /**
     * Headers builder for video requests. Implementations can override this method for custom headers.
     */
    protected open fun videoHeadersBuilder(): Headers.Builder {
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
     * override this method.
     *
     * @param page the page number to retrieve.
     * @param query the search query.
     * @param filters the list of filters to apply.
     */
    override fun fetchSearchAnime(page: Int, query: String, filters: AnimeFilterList): Observable<AnimesPage> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for the search anime given the page.
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
     * Returns an observable with the updated details for a anime. Normally it's not needed to
     * override this method.
     *
     * @param anime the anime to be updated.
     */
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
     * Returns an observable with the updated episode list for a anime. Normally it's not needed to
     * override this method.
     *
     * @param anime the anime to look for episodes.
     */
    override fun fetchEpisodeList(anime: SAnime): Observable<List<SEpisode>> {
        throw Exception("Stub!")
    }

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
     * Sorts the video list
     * Override this according to the user's preference
     */
    protected open fun List<Video>.sort(): List<Video> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for getting the url to the source image. Override only if it's needed to
     * override the url, send different headers or request method like POST.
     *
     * @param video the chapter whose page list has to be fetched
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
     * Called before inserting a new episode into database. Use it if you need to override episode
     * fields, like the title or the episode number. Do not change anything to [anime].
     *
     * @param episode the episode to be added.
     * @param anime the anime of the episode.
     */
    open fun prepareNewEpisode(episode: SEpisode, anime: SAnime) {
    }

    /**
     * Returns the list of filters for the source.
     */
    override fun getFilterList(): AnimeFilterList {
        throw Exception("Stub!")
    }
}
