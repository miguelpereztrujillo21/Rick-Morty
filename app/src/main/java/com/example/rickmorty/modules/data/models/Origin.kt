package com.example.rickmorty.modules.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Origin(
    @ColumnInfo(name = "origin_name")
    var name: String,
    @ColumnInfo(name = "origin_url")
    var url: String? = null
)