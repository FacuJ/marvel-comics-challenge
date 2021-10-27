package com.facundojaton.marvelcomicschallenge.remote

import com.facundojaton.marvelcomicschallenge.model.Character
import com.facundojaton.marvelcomicschallenge.repositories.RemoteDataSource
import com.facundojaton.marvelcomicschallenge.utils.APIConstants
import javax.inject.Inject

class MarvelRemoteDataSource @Inject constructor(
    private val service: MarvelRemoteService
) : RemoteDataSource {

    override suspend fun getCharacters(page: Int): List<Character> {
       val params = HashMap<String, String>()
        params[APIConstants.QueryParams.APIKEY] = APIConstants.Keys.PUBLIC_KEY
        params[APIConstants.QueryParams.HASH] = APIConstants.Keys.PRIVATE_KEY
        params[APIConstants.QueryParams.TIMESTAMP] = APIConstants.Keys.TIMESTAMP
        val response = service.getCharactersList(params)
        //ToDo: foresee another codes
        if(response.code == "200") {
            response.data?.results?.let {
                return it
            }
        }
        return emptyList()
    }
}