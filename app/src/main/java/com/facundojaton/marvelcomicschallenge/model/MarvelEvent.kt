package com.facundojaton.marvelcomicschallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class MarvelEvent(
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
) : Parcelable, Serializable
