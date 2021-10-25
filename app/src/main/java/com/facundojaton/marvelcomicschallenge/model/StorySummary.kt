package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class StorySummary(
    val resourceURI: String?,
    val name: String?,
    val type: String?
) : Serializable