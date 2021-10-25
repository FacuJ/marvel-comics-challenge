package com.facundojaton.marvelcomicschallenge.db

import com.facundojaton.marvelcomicschallenge.model.Character
import com.facundojaton.marvelcomicschallenge.repositories.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataSource @Inject constructor(private val dao: MarvelDao) : LocalDataSource {

    /*override suspend fun size(): Int = dao.characterCount()

    override suspend fun saveCharacters(characters: List<Character>) {
        dao.insertCharacters(characters.map {
            it.toRoomCharacter()
        })
    }

    override fun getCharacters(): Flow<List<Character>> =
        dao.getAllCharacters().map { characters ->
            characters.map {
                it.toDomainCharacter()
            }
        }*/
}