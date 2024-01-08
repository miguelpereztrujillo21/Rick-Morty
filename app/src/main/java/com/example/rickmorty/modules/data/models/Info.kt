package com.example.rickmorty.modules.data.models

import com.google.gson.annotations.SerializedName

data class Info(
    val count: Int? = null,
    val pages: Int? = null,
    @SerializedName("next") val mNext: String? = null,
    val prev: String? = null
)