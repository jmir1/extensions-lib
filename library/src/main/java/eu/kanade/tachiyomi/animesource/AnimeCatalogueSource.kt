@file:Suppress("UNUSED")

package eu.kanade.tachiyomi.animesource

import eu.kanade.tachiyomi.animesource.model.AnimeFilterList
import eu.kanade.tachiyomi.animesource.model.AnimesPage
import rx.Observable

@Deprecated(
    message = "Use the base AnimeSource class instead",
    replaceWith = ReplaceWith(
        expression = "AnimeSource",
        imports = ["eu.kanade.tachiyomi.animesource.AnimeSource"]
    )
)
interface AnimeCatalogueSource : AnimeSource {

    override val language: String get() = throw RuntimeException("Stub!")

    override val hasSearchFilters: Boolean get() = throw RuntimeException("Stub!")

    override val hasLatestListing: Boolean get() = throw RuntimeException("Stub!")

    override suspend fun getSearchFilters(): AnimeFilterList = throw RuntimeException("Stub!")

    override suspend fun getDefaultAnimeList(page: Int): AnimesPage = throw RuntimeException("Stub!")

    override suspend fun getLatestAnimeList(page: Int): AnimesPage = throw RuntimeException("Stub!")

    override suspend fun getAnimeList(
        query: String,
        filters: AnimeFilterList,
        page: Int
    ): AnimesPage = throw RuntimeException("Stub!")

    /**
     * An ISO 639-1 compliant language code (two letters in lower case).
     */
    @Deprecated("Use language instead", ReplaceWith("language"))
    val lang: String get() = throw RuntimeException("Stub!")

    /**
     * Whether the source has support for latest updates.
     */
    @Deprecated("Use hasLatestListing instead", ReplaceWith("hasLatestListing"))
    val supportsLatest: Boolean get() = throw RuntimeException("Stub!")

    /**
     * Returns the list of filters for the source.
     */
    @Deprecated("Use the new suspend API instead", ReplaceWith("getSearchFilters"))
    fun getFilterList(): AnimeFilterList = throw RuntimeException("Stub!")

    @Deprecated(
        message = "Use getDefaultAnimeList instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith("getDefaultAnimeList")
    )
    suspend fun getPopularAnime(page: Int): AnimesPage = throw RuntimeException("Stub!")

    @Deprecated(
        message = "Use getLatestAnimeList instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith("getLatestAnimeList")
    )
    suspend fun getLatestUpdates(page: Int): AnimesPage = throw RuntimeException("Stub!")

    @Deprecated(
        message = "Use getAnimeList instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith("getAnimeList(query, filters, page)")
    )
    suspend fun getSearchAnime(page: Int, query: String, filters: AnimeFilterList): AnimesPage = throw RuntimeException("Stub!")

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
