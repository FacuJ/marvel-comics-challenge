package com.facundojaton.marvelcomicschallenge.remote

import com.facundojaton.marvelcomicschallenge.model.CharactersListResponse
import com.facundojaton.marvelcomicschallenge.model.ComicsListResponse
import com.facundojaton.marvelcomicschallenge.model.EventsListResponse
import com.facundojaton.marvelcomicschallenge.utils.APIConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import kotlin.collections.HashMap

interface MarvelRemoteService {

    @GET(APIConstants.Endpoints.CHARACTERS)
    suspend fun getCharactersList(@QueryMap params : HashMap<String, String>): CharactersListResponse

    @GET(APIConstants.Endpoints.EVENTS)
    suspend fun getEventsList(@QueryMap params : HashMap<String, String>): EventsListResponse

    @GET(APIConstants.Endpoints.COMICS)
    suspend fun getComicsList(
        @QueryMap params : HashMap<String, String>
    ): ComicsListResponse

    @GET(APIConstants.Endpoints.CHARACTER_COMICS)
    suspend fun getCharacterComicsList(
        @Path(value = APIConstants.QueryParams.CHARACTER_ID) eventId: String,
        @QueryMap params : HashMap<String, String>
    ): ComicsListResponse

    @GET(APIConstants.Endpoints.EVENT_COMICS)
    suspend fun getSingleEventComicsList(
        @Path(value = APIConstants.QueryParams.EVENT_ID) eventId: String,
        @QueryMap params : HashMap<String, String>
    ): ComicsListResponse

}