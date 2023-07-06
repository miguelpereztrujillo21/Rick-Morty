package com.example.rickmorty.modules.modules.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.RetrofitHelper
import com.example.rickmorty.modules.models.Character
import com.example.rickmorty.modules.models.Info
import com.example.rickmorty.modules.models.ResponseCharacters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {
    var characters = MutableLiveData<ArrayList<Character>>()
    val filterText = MutableLiveData<String>()
    var currentPage = 1
    var isLoading = false
    var info: Info? = null

    var error = MutableLiveData<String>()
    fun getCharacters(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    RetrofitHelper.getInstance().create(Api::class.java).getCharacters(page)
                if (response.isSuccessful){
                    val updatedList = ArrayList<Character>()
                    updatedList.addAll(characters.value ?: emptyList())
                    updatedList.addAll(response.body()?.results ?: emptyList())
                    characters.postValue(updatedList)
                    response.body()?.info?.let { info = it }
                } else {
                    error.postValue("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                error.postValue("Error: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun getFilteredCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    RetrofitHelper.getInstance().create(Api::class.java).getCharacters(name = filterText.value)
                if (response.isSuccessful) {
                    characters.postValue(response.body()?.results)
                    response.body()?.info?.let { info = it }
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