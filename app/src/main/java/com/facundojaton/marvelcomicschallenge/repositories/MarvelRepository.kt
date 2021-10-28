package com.facundojaton.marvelcomicschallenge.repositories

import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {

    suspend fun getCharacters(): List<MarvelCharacter> = remote.getCharacters(1)

    suspend fun getEvents() : List<MarvelEvent> = remote.getEvents(1)

}

interface RemoteDataSource {
    suspend fun getCharacters(page: Int): List<MarvelCharacter>
    suspend fun getEvents(page: Int): List<MarvelEvent>
}

interface LocalDataSource {
    /*suspend fun size(): Int
    suspend fun saveCharacters(characters: List<Character>)
    fun getCharacters(): Flow<List<Character>>*/
}