package com.example.rickmorty.modules.modules.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.RetrofitHelper
import com.example.rickmorty.modules.models.Character
import com.example.rickmorty.modules.models.Info
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var characters = MutableLiveData<ArrayList<Character>>()
    val filterText = MutableLiveData<String>()
    var currentPage = 1
    var maxPages: Int? = null
    var isLoading = false
    private var info: Info? = null
    var cacheFilteredCharacters = false


    var error = MutableLiveData<String>()


    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    RetrofitHelper.getInstance().create(Api::class.java)
                        .getCharacters(page = currentPage, name = filterText.value)
                
                if (response.isSuccessful) {
                    val updatedList = ArrayList<Character>()
                    if (cacheFilteredCharacters) {
                        updatedList.addAll(characters.value ?: emptyList())
                    }
                    updatedList.addAll(response.body()?.results ?: emptyList())

                    characters.postValue(updatedList)
                    maxPages = response.body()?.info?.pages
                    response.body()?.info?.let { info = it }
                    cacheFilteredCharacters = false
                } else {
                    error.postValue("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                error.value = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}