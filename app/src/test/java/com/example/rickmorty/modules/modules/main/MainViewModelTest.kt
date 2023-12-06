package com.example.rickmorty.modules.modules.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.RetrofitHelper
import com.example.rickmorty.modules.data.models.Character
import com.example.rickmorty.modules.data.models.ResponseCharacters
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import io.mockk.mockk


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import retrofit2.Response

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var viewModel: MainViewModel
     var api = RetrofitHelper.getInstance().create(Api::class.java)

    @Before
    fun setup() {
        api = mockk(relaxed = true)
        viewModel = MainViewModel()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
     @Test
     fun `when the observer receives empylist`() {
         //Given
         val observer = mockk<Observer<ArrayList<Character>>>()
         every { observer.onChanged(any()) }just Runs
         viewModel.characters.observeForever(observer)

         //When
         viewModel.characters.value = ArrayList<Character>()
         //Then
         coVerify {observer.onChanged(ArrayList<Character>()) }

     }

    @Test
    suspend fun `test getCharacters failure server and null list in results`() {

        val responseMock = mockk<Response<ResponseCharacters>>(relaxed = true)

        every { responseMock.isSuccessful } returns false
        every { responseMock.code() } returns 500
        every { responseMock.body()?.results } returns  null
        coEvery { api.getCharacters(any()) } returns responseMock
        coVerify { api.getCharacters(any(), any(), any(), any()) }

        api.getCharacters()

        assert(responseMock.body()?.results == null)

    }



}
