package com.facundojaton.marvelcomicschallenge.remote

import com.facundojaton.marvelcomicschallenge.model.Character
import com.facundojaton.marvelcomicschallenge.repositories.RemoteDataSource

class MarvelRemoteDataSource(private val service: MarvelRemoteService) : RemoteDataSource {

    override suspend fun getCharacters(page: Int): List<Character> =
        service.getCharactersList()
}