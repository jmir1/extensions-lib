@file:Suppress("UNUSED", "UNUSED_PARAMETER")

package eu.kanade.tachiyomi.animesource.model

import okhttp3.Headers

/**
 * A sub/dub track.
 */
data class Track(val url: String, val lang: String)

/**
 * Type of video chapter
 *
 * @since extensions-lib 16
 */
enum class ChapterType {
    Opening,
    Ending,
    Recap,
    MixedOp,
    Other,
}

/**
 * Data class for representing a video chapter
 *
 * @since extensions-lib 16
 *
 * @property start start position of chapter
 * @property end   end position of chapter
 * @property name  name of chapter
 * @property type  type of chapter
 */
data class TimeStamp(
    val start: Double,
    val end: Double,
    val name: String,
    val type: ChapterType = ChapterType.Other,
)

/**
 * The instance that contains the data needed to watch a video.
 *
 * @since extensions-lib 16
 *
 * @property videoUrl       the url to the streamable video
 * @property videoTitle     the name of the video
 * @property resolution     resolution of video, useful for sorting
 * @property bitrate        bitrate of video, useful for sorting
 * @property headers        headers for video
 * @property preferred      boolean to indicate whether a video is preferred.
 *                          Preferred videos will be picked over non-preferred ones.
 * @property subtitleTracks subtitles for video
 * @property audioTracks    audio tracks for video
 * @property timestamps     list of custom chapters
 * @property internalData   data used by the extension. Useful for `resolveVideo`
 */
data class Video(
    var videoUrl: String,
    val videoTitle: String,
    val resolution: Int? = null,
    val bitrate: Int? = null,
    val headers: Headers? = null,
    val preferred: Boolean = false,
    val subtitleTracks: List<Track> = emptyList(),
    val audioTracks: List<Track> = emptyList(),
    val timestamps: List<TimeStamp> = emptyList(),
    val internalData: String = "",
) {
    @Deprecated(
        message = "Use the new Video constructor",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith(
            expression = "Video(videoTitle = quality, videoUrl = videoUrl, headers = headers, subtitleTracks = subtitleTracks, audioTracks = audioTracks)",
        )
    )
    constructor(
        url: String,
        quality: String,
        videoUrl: String?,
        headers: Headers? = null,
        subtitleTracks: List<Track> = emptyList(),
        audioTracks: List<Track> = emptyList(),
    ) : this(
        videoTitle = quality,
        videoUrl = videoUrl ?: "null",
        headers = headers,
        subtitleTracks = subtitleTracks,
        audioTracks = audioTracks,
    )
}
