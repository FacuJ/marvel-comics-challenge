package com.facundojaton.marvelcomicschallenge.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.facundojaton.marvelcomicschallenge.utils.UrlConverter

@Entity
data class RoomCharacter(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String?,
    val description: String?,
    val modified : String?,
    val resourceUri: String?,
    /*
    @TypeConverters(UrlConverter::class)
    val urls: List<Url>,

    @Embedded val thumbnail: Image?,
    @Embedded val comics : ComicList?,
    @Embedded val stories: StoryList?,
    @Embedded val events: EventList?,
    @Embedded val series: SeriesList?
)*/
)