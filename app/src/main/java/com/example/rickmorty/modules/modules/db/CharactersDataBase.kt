package com.example.rickmorty.modules.modules.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickmorty.modules.data.models.CharacterDao
import com.example.rickmorty.modules.data.models.Character
import com.example.rickmorty.modules.modules.Converters

@Database(entities = [Character::class], version = 2)
    @TypeConverters(Converters::class)
    abstract class CharactersDataBase : RoomDatabase() {

        abstract fun characterDao(): CharacterDao

        companion object {
            @Volatile
            private var instance:  CharactersDataBase? = null
            private var LOCK = Any()

            operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
                instance ?: getDataBase(context).also { instance = it }
            }

            @Synchronized
            fun getDataBase(context: Context):  CharactersDataBase {
                return instance ?: synchronized(this) {
                    Room.databaseBuilder(context,  CharactersDataBase::class.java, "characters_database")
                        .build()
                }
            }
        }
    }
