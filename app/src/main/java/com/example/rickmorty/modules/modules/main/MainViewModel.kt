package com.example.rickmorty.modules.modules.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.ApiRepository
import com.example.rickmorty.modules.api.RetrofitApiService
import com.example.rickmorty.modules.data.models.Character
import com.example.rickmorty.modules.data.models.Info
import com.example.rickmorty.modules.helpers.Constants
import com.example.rickmorty.modules.helpers.Constants.NOT_RESULTS
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    var characters = MutableLiveData<ArrayList<Character>>()
    val filterText = MutableLiveData<String>()
    val filterStatus = MutableLiveData<String>()
    val filterGender = MutableLiveData<String>()
    var currentPage = 1
    private var maxPages: Int? = null
    var isLoading = false
    private var info: Info? = null
    private var cacheFilteredCharacters = false
    var error = MutableLiveData<String>()

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val response = apiRepository.getCharacters(
                    page = currentPage,
                    name = filterText.value,
                    status = filterStatus.value,
                    gender = filterGender.value
                )
                isLoading = true
                val updatedList = ArrayList<Character>()
                if (cacheFilteredCharacters) {
                    updatedList.addAll(characters.value ?: emptyList())
                }
                updatedList.addAll(response.results ?: emptyList())

                characters.postValue(updatedList)
                maxPages = response.info?.pages
                response.info?.let { info = it }
                cacheFilteredCharacters = false
            } catch (e: Exception) {
                handleException(e)
            } finally {
                isLoading = false
            }
        }
    }

    private fun handleException(e: Exception) {
        if (e.message?.equals(NOT_RESULTS) == true) {
            error.postValue(NOT_RESULTS)
            characters.postValue(ArrayList())
        } else {
            error.postValue("Error: ${e.message}")
            characters.postValue(ArrayList())
        }
    }


    fun onChipCheckedChanged(isChecked: Boolean, filter: String, isStatus: Boolean) {
        filterStatus.value = if (isStatus && isChecked) filter else ""
        filterGender.value = if (isChecked && !isStatus) filter else ""
        currentPage = 0
    }

    fun handlePagination() {
        maxPages?.let { maxPages ->
            if (maxPages > currentPage) {
                currentPage += 1
                getCharacters()
                cacheFilteredCharacters = true
            } else {
                currentPage = 1
            }
        }
    }
}