package com.example.rickmorty.modules

import android.app.Application
import androidx.room.Room
import com.example.rickmorty.modules.modules.db.CharactersDataBase

class App: Application() {
    companion object {
        lateinit var instance: App private set
        lateinit var db: CharactersDataBase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = Room.databaseBuilder(
            instance,
            CharactersDataBase::class.java,
            "rick&morty-database.db"
        ).fallbackToDestructiveMigration().build()
    }
}


