package com.facundojaton.marvelcomicschallenge.repositories

import com.facundojaton.marvelcomicschallenge.model.Character
import com.facundojaton.marvelcomicschallenge.remote.MarvelRemoteService
import kotlinx.coroutines.flow.Flow
import java.util.HashMap
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {
    suspend fun getCharacters(queryParams: HashMap<String, String>): List<Character> {
        return remote.getCharacters(1)
    }
    //fun getCharacters() : Flow<List<Character>> = local.getCharacters()
}

interface RemoteDataSource {
    suspend fun getCharacters(page: Int): List<Character>
}

interface LocalDataSource {
    /*suspend fun size(): Int
    suspend fun saveCharacters(characters: List<Character>)
    fun getCharacters(): Flow<List<Character>>*/
}