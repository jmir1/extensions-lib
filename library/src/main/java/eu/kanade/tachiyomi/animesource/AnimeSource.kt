package eu.kanade.tachiyomi.animesource

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
     * Returns an observable with the updated details for a anime.
     *
     * @param anime the anime to update.
     */
    fun fetchAnimeDetails(anime: SAnime): Observable<SAnime>

    /**
     * Returns an observable with all the available episodes for a anime.
     *
     * @param anime the anime to update.
     */
    fun fetchEpisodeList(anime: SAnime): Observable<List<SEpisode>>

    /**
     * Returns an observable with a list of video for the episode of an anime.
     *
     * @param episode the anime to update.
     */
    fun fetchVideoList(episode: SEpisode): Observable<List<Video>>
}