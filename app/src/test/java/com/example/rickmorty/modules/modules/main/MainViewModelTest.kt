package com.example.rickmorty.modules.modules.main


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.ApiRepositoryImpl
import com.example.rickmorty.modules.helpers.Constants
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiRepository: ApiRepositoryImpl
    private lateinit var viewModel: MainViewModel


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mockWebServer = MockWebServer()
        Dispatchers.setMain(Dispatchers.Unconfined)
        apiRepository = ApiRepositoryImpl(
            Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))//Pass any base url like this
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        )
        viewModel = MainViewModel(apiRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCharactersSucess() = runBlocking {
        val mockResponse = createSuccessResponse()
        mockWebServer.enqueue(mockResponse)
        viewModel.getCharacters()
        delay(1000)

        val request = mockWebServer.takeRequest()
        assertThat(viewModel.characters.value).isNotNull()
        assertThat(viewModel.characters.value).hasSize(2)
        assertThat(viewModel.characters.value?.get(0)?.name).isEqualTo("Rick Sanchez")
        assertThat(viewModel.currentPage).isEqualTo(1)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCharactersNotResults() = runBlocking {
        val mockResponse = createNotResultsResponse()
        mockWebServer.enqueue(mockResponse)
        viewModel.getCharacters()
        delay(5000)

        assertThat(viewModel.error.value).isEqualTo(Constants.NOT_RESULTS)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
    }

    private fun createSuccessResponse(): MockResponse {
        val json = """
                {
                  "info": {
                    "count": 826,
                    "pages": 1,
                    "next": "https://rickandmortyapi.com/api/character/?page=2",
                    "prev": null
                  },
                  "results": [
                    {
                      "id": 1,
                      "name": "Rick Sanchez",
                      "status": "Alive",
                      "species": "Human",
                      "type": "",
                      "gender": "Male",
                      "origin": {
                        "name": "Earth (C-137)",
                        "url": "https://rickandmortyapi.com/api/location/1"
                      },
                      "location": {
                        "name": "Citadel of Ricks",
                        "url": "https://rickandmortyapi.com/api/location/3"
                      },
                      "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                      "episode": [
                        "https://rickandmortyapi.com/api/episode/1",
                        "https://rickandmortyapi.com/api/episode/2"
                      ],
                      "url": "https://rickandmortyapi.com/api/character/1",
                      "created": "2017-11-04T18:48:46.250Z"
                    },
                    {
                      "id": 2,
                      "name": "Morty Smith",
                      "status": "Alive",
                      "species": "Human",
                      "type": "",
                      "gender": "Male",
                      "origin": {
                        "name": "unknown",
                        "url": ""
                      },
                      "location": {
                        "name": "Citadel of Ricks",
                        "url": "https://rickandmortyapi.com/api/location/3"
                      },
                      "image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                      "episode": [
                        "https://rickandmortyapi.com/api/episode/1",
                        "https://rickandmortyapi.com/api/episode/2"
                      ],
                      "url": "https://rickandmortyapi.com/api/character/2",
                      "created": "2017-11-04T18:50:21.651Z"
                    }
                  ]
                }
            """.trimIndent()
        return MockResponse().setResponseCode(200).setBody(json)
    }

    private fun createNotResultsResponse(): MockResponse {
        val json = """{
                       "error": "There is nothing here"
                      }""".trimIndent()
         return MockResponse().setBody(json)
    }


}
