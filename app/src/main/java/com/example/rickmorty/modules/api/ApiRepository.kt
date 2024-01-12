package com.example.rickmorty.modules.api

import com.example.rickmorty.modules.data.models.ResponseCharacters

interface ApiRepository {
    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: String? = null,
        gender: String? = null
    ): ResponseCharacters

}