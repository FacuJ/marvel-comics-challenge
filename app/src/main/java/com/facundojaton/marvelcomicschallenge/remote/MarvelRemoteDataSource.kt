package com.facundojaton.marvelcomicschallenge.remote

import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent
import com.facundojaton.marvelcomicschallenge.repositories.RemoteDataSource
import com.facundojaton.marvelcomicschallenge.utils.APIConstants
import javax.inject.Inject

class MarvelRemoteDataSource @Inject constructor(
    private val service: MarvelRemoteService
) : RemoteDataSource {

    private val params = HashMap<String, String>()

    init {
        params[APIConstants.QueryParams.APIKEY] = APIConstants.Keys.PUBLIC_KEY
        params[APIConstants.QueryParams.HASH] = APIConstants.Keys.PRIVATE_KEY
        params[APIConstants.QueryParams.TIMESTAMP] = APIConstants.Keys.TIMESTAMP
    }

    override suspend fun getCharacters(page: Int): List<MarvelCharacter> {
        val response = service.getCharactersList(params)
        //ToDo: foresee another codes
        if(response.code == "200") {
            response.data?.results?.let {
                return it
            }
        }
        return emptyList()
    }

    override suspend fun getEvents(page: Int): List<MarvelEvent> {
        val response = service.getEventsList(params)
        //ToDo: foresee another codes
        if(response.code == "200") {
            response.data?.results?.let {
                return it
            }
        }
        return emptyList()
    }
}