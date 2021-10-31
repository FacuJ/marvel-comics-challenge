package com.facundojaton.marvelcomicschallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class MarvelCharacter(
    val id: Long,
    val name: String?,
    val description: String?,
    val modified : String?,
    val resourceUri: String?,
    val urls: ArrayList<Url>?,
    val thumbnail: Image?,
    val comics : ComicList?,
    val stories: StoryList?,
    val events: EventList?,
    val series: SeriesList?
) : Parcelable, Serializable
