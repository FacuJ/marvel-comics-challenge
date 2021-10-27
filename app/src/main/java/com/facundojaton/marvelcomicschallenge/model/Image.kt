package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class Image(
    val path: String?,
    val extension: String?
) : Serializable {

    fun getFullPath(): String {
        val newUrl = path?.replace("http:","https:")
        return "$newUrl.$extension"
    }
}