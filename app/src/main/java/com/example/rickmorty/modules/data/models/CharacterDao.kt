package com.example.rickmorty.modules.data.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  insert( character: Character )
    @Update
    suspend fun update( character: Character )
    @Delete
    suspend fun delete( character: Character )

    @Query("SELECT * from characters WHERE name = :name")
    fun getAdminByName(name: String): Flow<List<Character>>

    @Query("SELECT * from characters ORDER BY name ASC")
    fun getAllItems(): Flow<List<Character>>
}