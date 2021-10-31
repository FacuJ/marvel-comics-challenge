package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class ComicSummary(
    val resourceURI: String?,
    val name: String?
) : Serializable