package com.example.rickmorty.modules.modules.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.RetrofitHelper
import com.example.rickmorty.modules.models.ResponseCharacters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {
    var characters = MutableLiveData<ResponseCharacters>()

    var error = MutableLiveData<String>()
    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitHelper.getInstance().create(Api::class.java).getCharacters()
                if (response.isSuccessful) {
                    characters.postValue(response.body())
                } else {
                    error.postValue("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                error.value = "Error: ${e.message}"
            }
        }
    }
}