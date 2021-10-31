package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable
import java.text.SimpleDateFormat

data class ComicsListResponse(
    val code: String?,
    val status: String?,
    val data: ComicDataContainer?
) : Serializable

data class ComicDataContainer(
    val offset: Long?,
    val limit: Long?,
    val total: Long?,
    val count: Long?,
    val results: ArrayList<MarvelComic>?
) : Serializable

data class MarvelComic(
    val id: Long,
    val title: String?,
    val dates: ArrayList<ComicDate>?
) : Serializable {
    fun getOnSaleDateYear(): String?{
        dates?.map {
            if (it.type == "onsaleDate") return it.getYear()
        }
        return null
    }
}

data class ComicDate(
    val type: String?,
    val date: String?
) : Serializable {
    fun getYear(): String? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        date?.let {
            val newDate = format.parse(date)
            val newFormat = SimpleDateFormat("yyyy")
            return newFormat.format(newDate)
        }
        return ""
    }
}
