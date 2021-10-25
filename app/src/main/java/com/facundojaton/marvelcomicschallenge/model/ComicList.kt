package com.facundojaton.marvelcomicschallenge.model

import androidx.room.ColumnInfo
import java.io.Serializable

data class ComicList(
    val available: Long?,
    val returned: Long?,
    @ColumnInfo (name = "collection_uri") val collectionURI: String?,
    val items : ArrayList<ComicSummary>?
) : Serializable