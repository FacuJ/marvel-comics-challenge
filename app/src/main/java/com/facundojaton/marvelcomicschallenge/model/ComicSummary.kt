package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class ComicSummary(
    val resourceURI: String?,
    val name: String?
) : Serializable {

    //ToDo: remove year from name
    fun getNameWithoutYear() : String? = name

    //ToDo: remove name
    fun getYearWithoutName() : String? = name
}