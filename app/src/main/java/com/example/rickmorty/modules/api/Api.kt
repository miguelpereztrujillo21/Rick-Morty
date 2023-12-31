package com.example.rickmorty.modules.api

import com.example.rickmorty.modules.data.models.ResponseCharacters
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status:String? = null,
        @Query("gender") gender: String? = null
    ): Response<ResponseCharacters>


}