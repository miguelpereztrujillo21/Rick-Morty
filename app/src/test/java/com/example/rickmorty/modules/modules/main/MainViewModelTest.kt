package com.example.rickmorty.modules.modules.main


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmorty.modules.api.Api
import com.example.rickmorty.modules.api.ApiRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
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

        assertThat(viewModel.characters.value).isNotNull()
        assertThat(viewModel.characters.value).hasSize(2)
        assertThat(viewModel.characters.value?.get(0)?.name).isEqualTo("Rick Sanchez")
        assertThat(viewModel.currentPage).isEqualTo(1)
    }

/*    @Test
    fun `getCharacters() returns a list of characters when the API call is successful`() {
        // Simulamos una respuesta exitosa del API
        val response = createSuccessResponse()
        mockWebServer.enqueue(response)

        // Convertimos la respuesta JSON en una lista de personajes
        val characters = Gson().fromJson(response.getBody(), ResponseCharacters::class.java)

        // Simulamos la llamada al método `getCharacters()`
        viewModel.getCharacters()

        // Verificamos que la lista de personajes sea correcta
        assertThat(characters).isNotNull()
        assertThat(characters.results).hasSize(2)

        // Verificamos que el primer personaje tenga el nombre "Rick Sanchez"
        assertThat(characters.results?.get(0)?.name).isEqualTo("Rick Sanchez")
    }*/
    private fun createSuccessResponse(): MockResponse {
        val json =  """
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
            """
        return MockResponse()
            .setResponseCode(200)
            .setBody(json)
    }


    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
    }


}
