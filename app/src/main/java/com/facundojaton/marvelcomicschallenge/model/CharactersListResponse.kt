package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class CharactersListResponse(
    val code: String?,
    val status: String?,
    val copyright: String?,
    /* val attributionText": "Data provided by Marvel. © 2021 MARVEL",
     val attributionHTML": "<a href=\"http://marvel.com\">Data provided by Marvel. © 2021 MARVEL</a>",
     val etag": "ef42466c108a2baf7b3cb10f050da18f5d2e4bcd",
     */val data: CharacterResponseData?
) : Serializable

data class CharacterResponseData(
    val offset: Long?,
    val limit: Long?,
    val total: Long?,
    val count: Long?,
    val results: ArrayList<Character>?
) : Serializable