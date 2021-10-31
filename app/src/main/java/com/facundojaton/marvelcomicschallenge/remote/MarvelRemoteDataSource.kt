package com.facundojaton.marvelcomicschallenge.remote

import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent
import com.facundojaton.marvelcomicschallenge.repositories.RemoteDataSource
import com.facundojaton.marvelcomicschallenge.utils.APIConstants
import javax.inject.Inject

class MarvelRemoteDataSource @Inject constructor(
    private val service: MarvelRemoteService
) : RemoteDataSource {

    override suspend fun getCharacters(page: Int): List<MarvelCharacter> {
        val params = HashMap<String, String>()
        buildCharacterParams(params, page)
        val response = service.getCharactersList(params)

        if(response.code == "200") {
            response.data?.results?.let {
                return it
            }
        }
        return emptyList()
    }

    override suspend fun getEvents(page: Int): List<MarvelEvent> {
        val params = HashMap<String, String>()
        buildEventsParams(params, page)
        val response = service.getEventsList(params)
        if(response.code == "200") {
            response.data?.results?.let {
                return it
            }
        }
        return emptyList()
    }

    private fun buildEventsParams(params: java.util.HashMap<String, String>, page: Int) {
        buildParams(params)
        params[APIConstants.QueryParams.LIMIT] = "25"
        params[APIConstants.QueryParams.ORDER_BY] = "startDate"
    }

    private fun buildCharacterParams(params: java.util.HashMap<String, String>, page: Int) {
        buildParams(params)
        params[APIConstants.QueryParams.LIMIT] = "15"
        if (page > 1) params[APIConstants.QueryParams.OFFSET] = "${(page - 1)*15}"
    }

    private fun buildParams(params: HashMap<String, String>) {
        params[APIConstants.QueryParams.APIKEY] = APIConstants.Keys.APIKEY
        params[APIConstants.QueryParams.HASH] = APIConstants.Keys.HASH
        params[APIConstants.QueryParams.TIMESTAMP] = APIConstants.Keys.TIMESTAMP
    }
}