package eu.kanade.tachiyomi.animesource.model

import android.net.Uri

data class Video(val url: String, val quality: String, var videoUrl: String?, var uri: Uri?)