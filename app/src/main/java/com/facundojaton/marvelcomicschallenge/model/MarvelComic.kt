package com.facundojaton.marvelcomicschallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import okhttp3.internal.format
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class MarvelComic(
    val id: Long,
    val title: String?,
    val description: String?,
    val modified: String?,
    val resourceUri: String?,
    val urls: ArrayList<Url>?,
    val start: String?,
    val end: String?,
    val thumbnail: Image?,
    val comics: ComicList?,
    val stories: StoryList?,
    val events: EventList?,
    val series: SeriesList?
    /*,
    val characters: StoryList?,
    val creators: StoryList?*/
) : Parcelable, Serializable {
    fun getStartDate() :String? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        start?.let {
            val newDate = format.parse(start)
            val newFormat = SimpleDateFormat("dd 'de' LLLL yyyy")
            return newFormat.format(newDate)
        }
        return ""
    }
}
