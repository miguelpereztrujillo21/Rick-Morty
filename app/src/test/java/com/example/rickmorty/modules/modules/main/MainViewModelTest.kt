package com.example.rickmorty.modules.modules.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.RetrofitHelper
import com.example.rickmorty.modules.models.Character
import com.example.rickmorty.modules.models.Info
import com.example.rickmorty.modules.models.ResponseCharacters
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private var api = RetrofitHelper.getInstance().create(Api::class.java)

    @Before
    fun setup() {
        api = mockk(relaxed = true)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `test getCharacters success`() {
        val responseMock = mockk<Response<ResponseCharacters>>(relaxed = true)
        val charactersMock = mockk<ArrayList<Character>>()
        val infoMock = mockk<Info>()

        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns ResponseCharacters(charactersMock, infoMock)
        coEvery { api.getCharacters(any()) } returns responseMock

        val observer = mockk<Observer<List<Character>>>(relaxed = true)



        viewModel.getCharacters(1)
    }

    @Test
    fun `test getCharacters failure server and null list in results`() {

        val responseMock = mockk<Response<ResponseCharacters>>(relaxed = true)

        every { responseMock.isSuccessful } returns false
        every { responseMock.code() } returns 500
        every { responseMock.body()?.results } returns  null
        coEvery { api.getCharacters(any()) } returns responseMock


        viewModel.getCharacters(1)

        assert(responseMock.body()?.results == null)

    }

    // Tests similares a los de getCharacters se pueden escribir para el método getFilteredCharacters

}
