package eu.kanade.tachiyomi.animesource

/**
 * A factory for creating multiple sources at runtime. Use this in case of a source 
 * that supports multiple languages or mirrors of the same website.
 */
interface AnimeSourceFactory {
    /**
     * Create a new copy of the sources
     * @return The created sources
     */
    fun createSources(): List<AnimeSource>
}
