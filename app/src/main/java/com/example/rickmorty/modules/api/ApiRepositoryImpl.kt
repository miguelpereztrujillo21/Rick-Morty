package com.example.rickmorty.modules.api

import com.example.rickmorty.modules.data.models.ResponseCharacters
import com.example.rickmorty.modules.helpers.Constants
import com.google.gson.JsonParser

class ApiRepositoryImpl(private val api: Api) : ApiRepository {
    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        status: String?,
        gender: String?
    ): ResponseCharacters {


        return try {
            val response = api.getCharacters(page, name, status, gender)
            if (response.isSuccessful) {
                response.body() ?: ResponseCharacters()
            } else {
                val jsonObject = JsonParser().parse(response.errorBody()?.string()).asJsonObject
                val errorValue = jsonObject.get("error").asString
                if(errorValue?.equals(Constants.NOT_RESULTS) == true) {
                    throw Exception(Constants.NOT_RESULTS)
                }else{
                    throw Exception("Error en la solicitud: ${response.code()}")
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}