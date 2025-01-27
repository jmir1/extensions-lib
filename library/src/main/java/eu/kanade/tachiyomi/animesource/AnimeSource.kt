@file:Suppress("UNUSED")

package eu.kanade.tachiyomi.animesource

import eu.kanade.tachiyomi.animesource.model.AnimeFilterList
import eu.kanade.tachiyomi.animesource.model.AnimesPage
import eu.kanade.tachiyomi.animesource.model.Video
import eu.kanade.tachiyomi.animesource.model.SEpisode
import eu.kanade.tachiyomi.animesource.model.SAnime
import rx.Observable

/**
 * A basic interface for creating a source. It could be an online source, a local source, etc...
 */
interface AnimeSource {

    /**
     * Id for the source. Must be unique.
     */
    val id: Long

    /**
     * Name of the source.
     */
    val name: String

    /**
     * Represents an IETF BCP 47 compliant language tag.
     * Special cases include:
     * - [Language.MULTI]: Indicates multiple languages.
     * - [Language.OTHER]: Refers to a language not explicitly defined by the source (e.g. text less, unofficially supported language).
     * - 'all': Indicates multiple language.
     *
     * Usage of 'all' is highly discouraged and is only supported due to legacy reasons.
     *
     * @since extensions-lib 16
     */
    val language: String

    /**
     * Indicates if the source supports search filters
     *
     * @since extensions-lib 16
     */
    val hasSearchFilters: Boolean

    /**
     * Whether the source has a list for latest updates
     */
    val hasLatestListing: Boolean

    /**
     * Returns the list of filters for the source.
     *
     * @since extensions-lib 16
     */
    suspend fun getSearchFilters(): AnimeFilterList

    /**
     * Get a page with a list of anime.
     *
     * @since extensions-lib 16
     *
     * @param page the page number to retrieve.
     */
    suspend fun getDefaultAnimeList(page: Int): AnimesPage

    /**
     * Get a page with a list of latest anime updates.
     *
     * @since extensions-lib 16
     *
     * @param page the page number to retrieve.
     */
    suspend fun getLatestAnimeList(page: Int): AnimesPage = throw RuntimeException("Stub!")

    /**
     * Get a page with a list of anime.
     *
     * @since extensions-lib 16
     *
     * @param query     the search query.
     * @param filters   the list of filters to apply.
     * @param page      the page number to retrieve.
     */
    suspend fun getAnimeList(query: String, filters: AnimeFilterList, page: Int): AnimesPage

    /**
     * Get the updated details for an aime and its episodes
     *
     * @since extensions-lib 16
     *
     * @param anime         anime to get details and episodes for.
     * @param updateAnime   whether to update the anime details or not
     * @param fetchEpisodes whether to fetch episodes or not.
     */
    suspend fun getAnimeDetailsAndEpisodes(
        anime: SAnime,
        updateAnime: Boolean,
        fetchEpisodes: Boolean,
    ): Pair<SAnime, List<SEpisode>> = throw RuntimeException("Stub!")

    /**
     * Get the list of videos a episode has.
     *
     * @since extensions-lib 14
     *
     * @param episode the episode.
     */
    suspend fun getVideoList(episode: SEpisode): List<Video>

    /**
     * Object for holding the special cases supported by [AnimeSource.language].
     *
     * @since extensions-lib 16
     */
    object Language {
        /**
         * Indicates multiple languages.
         *
         * @since extensions-lib 16
         */
        const val MULTI = "multi"

        /**
         * Refers to a language not explicitly defined by the source (e.g. text less, unofficially supported language)
         *
         * @since extensions-lib 16
         */
        const val OTHER = "other"
    }

    @Deprecated(
        message = "Use the new combined API instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith("getAnimeDetailsAndEpisodes(anime, true, false)")
    )
    suspend fun getAnimeDetails(anime: SAnime): SAnime

    @Deprecated(
        message = "Use the new combined API instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith("getAnimeDetailsAndEpisodes(anime, false, true)")
    )
    suspend fun getEpisodeList(anime: SAnime): List<SEpisode>

    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getAnimeDetails"),
    )
    fun fetchAnimeDetails(anime: SAnime): Observable<SAnime>

    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getEpisodeList"),
    )
    fun fetchEpisodeList(anime: SAnime): Observable<List<SEpisode>>

    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getVideoList"),
    )
    fun fetchVideoList(episode: SEpisode): Observable<List<Video>>
}
