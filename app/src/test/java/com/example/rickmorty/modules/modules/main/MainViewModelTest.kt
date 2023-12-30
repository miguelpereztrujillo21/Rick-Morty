package com.example.rickmorty.modules.modules.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.RetrofitHelper
import com.example.rickmorty.modules.data.models.Character



import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import retrofit2.Response

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var  viewModel: MainViewModel

     var api = RetrofitHelper.getInstance().create(Api::class.java)

    @Before
    fun setup() {
        viewModel = MainViewModel()

    }



    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
     @Test
     fun `when the observer receives list the recycler is in screen`() {
        viewModel.characters.value = ArrayList()


     }

    @Test
    suspend fun `test getCharacters failure server and null list in results`() {


    }



}
