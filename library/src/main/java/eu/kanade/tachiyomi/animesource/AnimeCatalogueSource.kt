package eu.kanade.tachiyomi.animesource

import eu.kanade.tachiyomi.animesource.model.AnimeFilterList
import eu.kanade.tachiyomi.animesource.model.AnimesPage
import rx.Observable

interface AnimeCatalogueSource : AnimeSource {

    /**
     * An ISO 639-1 compliant language code (two letters in lower case).
     */
    @Deprecated("Use language instead", ReplaceWith("language"))
    val lang: String

    /**
     * Whether the source has support for latest updates.
     */
    val supportsLatest: Boolean

    @Deprecated(
        message = "Use getDefaultAnimeList instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith("getDefaultAnimeList")
    )
    suspend fun getPopularAnime(page: Int): AnimesPage {
       throw RuntimeException("Stub!") 
    }

    @Deprecated(
        message = "Use getAnimeList instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith("getAnimeList(query, filters, page)")
    )
    suspend fun getSearchAnime(page: Int, query: String, filters: AnimeFilterList): AnimesPage {
        throw RuntimeException("Stub!")
    }

    @Deprecated(
        message = "Use getLatestAnimeList instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith("getLatestAnimeList")
    )
    suspend fun getLatestUpdates(page: Int): AnimesPage {
        throw RuntimeException("Stub!")
    }

    @Deprecated("Use the new suspend variant instead", ReplaceWith("getSearchFilters"))
    fun getFilterList(): AnimeFilterList

    @Deprecated(
        "Use the new suspend variant instead",
        ReplaceWith("getDefaultAnimeList"),
    )
    fun fetchPopularAnime(page: Int): Observable<AnimesPage>

    @Deprecated(
        "Use the new suspend variant instead",
        ReplaceWith("getAnimeList"),
    )
    fun fetchSearchAnime(page: Int, query: String, filters: AnimeFilterList): Observable<AnimesPage>

    @Deprecated(
        "Use the new suspend variant instead",
        ReplaceWith("getLatestAnimeList"),
    )
    fun fetchLatestUpdates(page: Int): Observable<AnimesPage>
}
