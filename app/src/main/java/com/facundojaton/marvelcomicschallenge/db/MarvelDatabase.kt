package com.facundojaton.marvelcomicschallenge.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.facundojaton.marvelcomicschallenge.model.RoomCharacter

@Database(entities = [RoomCharacter::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun marvelDao(): MarvelDao
}