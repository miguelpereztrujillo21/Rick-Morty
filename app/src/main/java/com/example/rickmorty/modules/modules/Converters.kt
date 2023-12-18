package com.example.rickmorty.modules.modules

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromJson(value: String?): ArrayList<String>? {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun toString(value: ArrayList<String>?): String? {
        return value?.joinToString(",")
    }

}