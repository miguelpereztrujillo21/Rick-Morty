package com.example.rickmorty.modules.modules.main


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmorty.modules.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: Api
    private lateinit var mockWebServer: MockWebServer
    private lateinit var viewModel: MainViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel()
        mockWebServer = MockWebServer()
        Dispatchers.setMain(Dispatchers.Unconfined)
        /* viewModel.characters.observeForever {  }
          viewModel.error.observeForever {  }*/
        api = Retrofit.Builder().baseUrl(mockWebServer.url("/"))//Pass any base url like this
            .addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCharactersSucess() = runBlocking {

        val mockResponse = MockResponse().setResponseCode(200)
            .setBody(
            """
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
                        "https://rickandmortyapi.com/api/episode/2",
                        "https://rickandmortyapi.com/api/episode/51"
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
                        "https://rickandmortyapi.com/api/episode/2",
                        "https://rickandmortyapi.com/api/episode/3"
                      ],
                      "url": "https://rickandmortyapi.com/api/character/2",
                      "created": "2017-11-04T18:50:21.651Z"
                    }
                  ]
                }
            """)
        mockWebServer.enqueue(mockResponse)
        viewModel.getCharacters()

        delay(1000)
        assertNotNull(viewModel.characters.value)
       // assertThat(viewModel.characters.value?.size, `is`(2))
    }

    @Test
    fun `when the observer receives list the recycler is in screen`() {
        viewModel.characters.value = ArrayList()


    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
    }


}
