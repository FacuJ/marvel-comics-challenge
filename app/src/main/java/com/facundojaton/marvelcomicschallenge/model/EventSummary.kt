package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class EventSummary(
    val resourceURI: String?,
    val name: String?
) : Serializable