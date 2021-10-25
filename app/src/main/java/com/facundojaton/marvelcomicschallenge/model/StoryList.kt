package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class StoryList(
    val available: Long?,
    val returned: Long?,
    val collectionURI: String?,
    val items : ArrayList<StorySummary>?
) : Serializable