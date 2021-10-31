package com.facundojaton.marvelcomicschallenge.repositories

import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.MarvelComic
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {

    suspend fun getCharacters(page : Int): List<MarvelCharacter> = remote.getCharacters(page)

    suspend fun getEvents() : List<MarvelEvent> = remote.getEvents()

    suspend fun getCharacterComics(characterId: String) : List<MarvelComic> =
        remote.getCharacterComics(characterId)

    suspend fun getEventComics(eventId: String) : List<MarvelComic> =
        remote.getEventComics(eventId)

}

interface RemoteDataSource {
    suspend fun getCharacters(page: Int): List<MarvelCharacter>
    suspend fun getEvents(): List<MarvelEvent>
    suspend fun getCharacterComics(characterId : String) : List<MarvelComic>
    suspend fun getEventComics(eventId : String) : List<MarvelComic>
}

interface LocalDataSource {
    /*suspend fun size(): Int
    suspend fun saveCharacters(characters: List<Character>)
    fun getCharacters(): Flow<List<Character>>*/
}