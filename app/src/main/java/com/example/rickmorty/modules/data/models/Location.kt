package com.example.rickmorty.modules.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Location(
    @ColumnInfo(name = "location_name")
    var name: String? = null,
    @ColumnInfo(name = "location_url")
    var url: String? = null
)