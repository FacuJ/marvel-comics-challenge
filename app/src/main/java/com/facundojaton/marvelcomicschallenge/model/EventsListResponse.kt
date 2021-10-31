package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class EventsListResponse(
    val code: String?,
    val status: String?,
    val copyright: String?,
    val data: EventDataContainer?
) : Serializable

data class EventDataContainer(
    val offset: Long?,
    val limit: Long?,
    val total: Long?,
    val count: Long?,
    val results: ArrayList<MarvelEvent>?
) : Serializable