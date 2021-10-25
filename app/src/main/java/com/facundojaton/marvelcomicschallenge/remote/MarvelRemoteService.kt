package com.facundojaton.marvelcomicschallenge.remote

import com.facundojaton.marvelcomicschallenge.model.Character
import com.facundojaton.marvelcomicschallenge.utils.APIConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.*

interface MarvelRemoteService {

    @GET(APIConstants.Endpoints.CHARACTERS)
    suspend fun getCharactersList(): List<Character>
/*

    @GET(APIConstants.Endpoints.SHOWS)
    suspend fun getShowsByParams(@QueryMap params: HashMap<String, String>): List<Series>

    @GET("${APIConstants.Endpoints.SHOWS}/{series_id}/episodes")
    suspend fun getShowEpisodesById(@Path(value = "series_id") seriesId: Long): List<Episode>
*/

}