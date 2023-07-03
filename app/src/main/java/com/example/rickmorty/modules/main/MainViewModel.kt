package com.example.rickmorty.modules.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.RetrofitHelper
import com.example.rickmorty.modules.models.ResponseCharacters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {
    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = RetrofitHelper.getInstance().create(Api::class.java).getCharacters();
        }
    }
}