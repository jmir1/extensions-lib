package eu.kanade.tachiyomi.animesource.online

import eu.kanade.tachiyomi.animesource.model.AnimesPage
import eu.kanade.tachiyomi.animesource.model.Video
import eu.kanade.tachiyomi.animesource.model.SEpisode
import eu.kanade.tachiyomi.animesource.model.SAnime
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

/**
 * A simple implementation for sources from a website using Jsoup, an HTML parser.
 */
@Suppress("unused", "unused_parameter")
abstract class ParsedAnimeHttpSource : AnimeHttpSource() {

    /**
     * Parses the response from the site and returns a [AnimesPage] object.
     * @param response the response from the site.
     */
    override fun popularAnimeParse(response: Response): AnimesPage {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each anime.
     */
    abstract fun popularAnimeSelector(): String

    /**
     * Returns a anime from the given [element]. Most sites only show the title and the url, it's
     * totally fine to fill only those two values.
     *
     * @param element an element obtained from [popularAnimeSelector].
     */
    abstract fun popularAnimeFromElement(element: Element): SAnime

    /**
     * Returns the Jsoup selector that returns the <a> tag linking to the next page, or null if
     * there's no next page.
     */
    abstract fun popularAnimeNextPageSelector(): String?

    /**
     * Parses the response from the site and returns a [AnimesPage] object.
     *
     * @param response the response from the site.
     */
    override fun searchAnimeParse(response: Response): AnimesPage {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each anime.
     */
    abstract fun searchAnimeSelector(): String

    /**
     * Returns a anime from the given [element]. Most sites only show the title and the url, it's
     * totally fine to fill only those two values.
     *
     * @param element an element obtained from [searchAnimeSelector].
     */
    abstract fun searchAnimeFromElement(element: Element): SAnime

    /**
     * Returns the Jsoup selector that returns the <a> tag linking to the next page, or null if
     * there's no next page.
     */
    abstract fun searchAnimeNextPageSelector(): String?

    /**
     * Parses the response from the site and returns a [AnimesPage] object.
     *
     * @param response the response from the site.
     */
    override fun latestUpdatesParse(response: Response): AnimesPage {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each anime.
     */
    abstract fun latestUpdatesSelector(): String

    /**
     * Returns a anime from the given [element]. Most sites only show the title and the url, it's
     * totally fine to fill only those two values.
     *
     * @param element an element obtained from [latestUpdatesSelector].
     */
    abstract fun latestUpdatesFromElement(element: Element): SAnime

    /**
     * Returns the Jsoup selector that returns the <a> tag linking to the next page, or null if
     * there's no next page.
     */
    abstract fun latestUpdatesNextPageSelector(): String?

    /**
     * Parses the response from the site and returns the details of a anime.
     *
     * @param response the response from the site.
     */
    override fun animeDetailsParse(response: Response): SAnime {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the details of the anime from the given [document].
     *
     * @param document the parsed document.
     */
    abstract fun animeDetailsParse(document: Document): SAnime

    /**
     * Parses the response from the site and returns a list of episodes.
     *
     * @param response the response from the site.
     */
    override fun episodeListParse(response: Response): List<SEpisode> {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each episode.
     */
    abstract fun episodeListSelector(): String

    /**
     * Returns a episode from the given element.
     *
     * @param element an element obtained from [episodeListSelector].
     */
    abstract fun episodeFromElement(element: Element): SEpisode

    /**
     * Parses the response from the site and returns a list of videos.
     *
     * @param response the response from the site.
     */
    override fun videoListParse(response: Response): List<Video> {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each video.
     */
    abstract fun videoListSelector(): String

    /**
     * Returns a video from the given element.
     *
     * @param element an element obtained from [videoListSelector].
     */
    abstract fun videoFromElement(element: Element): Video

    override fun videoUrlParse(response: Response): String {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the absolute url to the video url from the document.
     *
     * @param document the parsed document.
     */
    abstract fun videoUrlParse(document: Document): String
}
