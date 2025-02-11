package com.example.famestream.networks

import com.example.famestream.model.PersonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("person/popular")
    suspend fun getPopularPersons(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int // Add this for pagination
    ): Response<PersonResponse>
}
