package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class CharactersListResponse(
    val code: String?,
    val status: String?,
    val copyright: String?,
    val data: CharacterResponseData?
) : Serializable

data class CharacterResponseData(
    val offset: Long?,
    val limit: Long?,
    val total: Long?,
    val count: Long?,
    val results: ArrayList<MarvelCharacter>?
) : Serializable