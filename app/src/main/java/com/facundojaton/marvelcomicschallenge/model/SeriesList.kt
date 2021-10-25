package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class SeriesList(
    val available: Long?,
    val returned: Long?,
    val collectionURI: String?,
    val items : ArrayList<SeriesSummary>?
) : Serializable