package com.facundojaton.marvelcomicschallenge.repositories

import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.MarvelComic
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val remote: RemoteDataSource
) {

    suspend fun getCharacters(page : Int): List<MarvelCharacter> = remote.getCharacters(page)

    suspend fun getEvents() : List<MarvelEvent> = remote.getEvents()

    suspend fun getCharacterComics(characterId: String) : List<MarvelComic> =
        remote.getCharacterComics(characterId)

    suspend fun getEventComics(eventIds: String, comicsPage: Int) : List<MarvelComic> =
        remote.getEventComics(eventIds, comicsPage)

    suspend fun getSingleEventComics(eventId: String, comicsPage: Int) : List<MarvelComic> =
        remote.getSingleEventComics(eventId, comicsPage)

}

interface RemoteDataSource {
    suspend fun getCharacters(page: Int): List<MarvelCharacter>
    suspend fun getEvents(): List<MarvelEvent>
    suspend fun getCharacterComics(characterId : String) : List<MarvelComic>
    suspend fun getEventComics(eventIds : String, comicsPage: Int) : List<MarvelComic>
    suspend fun getSingleEventComics(eventId : String, comicsPage: Int) : List<MarvelComic>
}