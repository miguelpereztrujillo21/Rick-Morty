package com.example.rickmorty.modules.modules.main.api

import com.example.rickmorty.modules.api.Api
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import org.hamcrest.MatcherAssert.assertThat
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: Api

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))//Pass any base url like this
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }
    @Test
    fun testGetCharacters() = runBlocking {
        val mockResponse = MockResponse().setResponseCode(200).setBody(
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

        val response = api.getCharacters()
        val responseBody = response.body()

        val request = mockWebServer.takeRequest()
        delay(1000)
        assertNotNull(request)
        assertNotNull(response.body())
        assertTrue(response.isSuccessful)

        assertThat(responseBody?.results?.size, `is`(2))
        assertThat(responseBody?.results?.get(0)?.id, `is`(1))
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}