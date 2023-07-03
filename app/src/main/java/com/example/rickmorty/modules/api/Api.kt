package com.example.rickmorty.modules.api

import com.example.rickmorty.modules.models.ResponseCharacters
import retrofit2.Response

import retrofit2.http.GET

interface Api {

    @GET("character/")
    suspend fun getCharacters(): Response<ResponseCharacters>


}