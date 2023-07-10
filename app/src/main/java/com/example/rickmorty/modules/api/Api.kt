package com.example.rickmorty.modules.api

import com.example.rickmorty.modules.models.ResponseCharacters
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status:String? = null
    ): Response<ResponseCharacters>


}