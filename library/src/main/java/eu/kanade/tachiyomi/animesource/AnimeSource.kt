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
     * - [Language.OTHER]: Refers to a language not explicitly defined.
     * - 'all': Indicates multiple language.
     *
     * Usage of 'all' is highly discouraged and is only supported due to legacy reasons.
     *
     * @since extensions-lib 16
     */
    val language: String

    /**
     * Indicates if the source supports search filters
     */
    val hasSearchFilters: Boolean

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
     * @param page the page number to retrieve.
     */
    suspend fun getDefaultAnimeList(page: Int): AnimesPage = throw Exception("Stub!")

    /**
     * Get a page with a list of latest anime updates.
     *
     * @since extensions-lib 16
     * @param page the page number to retrieve.
     */
    suspend fun getLatestAnimeList(page: Int): AnimesPage = throw Exception("Stub!")

    /**
     * Get a page with a list of anime.
     *
     * @since extensions-lib 16
     * @param query the search query.
     * @param filters the list of filters to apply.
     * @param page the page number to retrieve.
     */
    suspend fun getAnimeList(query: String, filters: AnimeFilterList, page: Int): AnimesPage = throw Exception("Stub!")

    /**
     * Get the updated details for an aime and its episodes
     *
     * @since extensions-lib 16
     * @param anime anime to get details and episodes for
     * @return the updated anime and its episodes
     */
    suspend fun getAnimeDetailsAndEpisodes(anime: SAnime): Pair<SAnime, List<SEpisode>> = throw Exception("Stub!")

    /**
     * Get the updated details for a anime.
     *
     * @since extensions-lib 14
     * @param anime the anime to update.
     * @return the updated anime.
     */
    suspend fun getAnimeDetails(anime: SAnime): SAnime

    /**
     * Get all the available episodes for a anime.
     *
     * @since extensions-lib 14
     * @param anime the anime to update.
     * @return the episodes for the anime.
     */
    suspend fun getEpisodeList(anime: SAnime): List<SEpisode>

    /**
     * Get the list of videos a episode has.
     *
     * @since extensions-lib 14
     * @param episode the episode.
     * @return the videos for the episode.
     */
    suspend fun getVideoList(episode: SEpisode): List<Video>

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

    object Language {
        const val MULTI = "multi"
        const val OTHER = "other"
    }
}
