package com.example.rickmorty.modules.modules.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmorty.BuildConfig
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.RetrofitHelper
import com.example.rickmorty.modules.data.models.Character
import com.example.rickmorty.modules.data.models.Info
import com.example.rickmorty.modules.helpers.Constants
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
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
        CoroutineScope(Dispatchers.Main).launch {
            val response =
                RetrofitHelper.getInstance().create(Api::class.java)
                    .getCharacters(
                        page = currentPage,
                        name = filterText.value,
                        status = filterStatus.value,
                        gender = filterGender.value)
            try {
                isLoading = true
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
                    val jsonObject = JsonParser().parse(response.errorBody()?.string()).asJsonObject
                    val errorValue = jsonObject.get("error").asString
                    if(errorValue?.equals(Constants.NOT_RESULTS) == true) {
                       error.postValue(Constants.NOT_RESULTS)
                        characters.postValue(ArrayList())
                    }else{
                        error.postValue("Error: ${response.code()}")
                        characters.postValue(ArrayList())
                    }
                }
                isLoading = false
            } catch (e: Exception) {
                error.value = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun onChipCheckedChanged(isChecked: Boolean, filter: String, isStatus: Boolean) {
        filterStatus.value = if (isStatus && isChecked) filter else ""
        filterGender.value = if (isChecked && !isStatus) filter else ""
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