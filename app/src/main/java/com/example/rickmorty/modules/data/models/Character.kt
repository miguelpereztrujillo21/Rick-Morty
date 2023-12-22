package com.example.rickmorty.modules.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickmorty.modules.modules.Converters
import java.io.Serializable

@Entity(tableName = "characters")
class Character: Serializable {
    @PrimaryKey
    var id: Int? = null
    var name: String? = null
    var status: String? = null
    var species: String? = null
    var type: String? = null
    var gender: String? = null
    @Embedded
    var origin: Origin? = null
    @Embedded
    var location: Location? = null
    var image: String? = null
    @TypeConverters(Converters::class)
    var episode: ArrayList<String>? = arrayListOf()
    var url: String? = null
    var created: String? = null
}