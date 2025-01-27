package eu.kanade.tachiyomi.animesource.model

interface SEpisode {

    var url: String

    var name: String

    var date_upload: Long

    var episode_number: Float

    var scanlator: String?

    companion object {
        fun create(): SEpisode {
            throw RuntimeException("Stub!")
        }
    }

}