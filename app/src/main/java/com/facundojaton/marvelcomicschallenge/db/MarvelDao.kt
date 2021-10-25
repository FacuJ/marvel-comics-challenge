package com.facundojaton.marvelcomicschallenge.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.facundojaton.marvelcomicschallenge.model.RoomCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {

   /* @Query("SELECT * FROM RoomCharacter")
    fun getAllCharacters(): Flow<List<RoomCharacter>>

    @Query("SELECT COUNT(id) FROM RoomCharacter")
    suspend fun characterCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters: List<RoomCharacter>)*/

}