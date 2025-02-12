package com.example.famestream.networks

import com.example.famestream.model.PersonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("person/popular")
    suspend fun getPopularPersons(
        @Query("api_key") apiKey: String = "dd6cc66d82bfef668d7eef1472ff768a",
        @Query("page") page: Int // Add this for pagination
    ): Response<PersonResponse>

    @GET("search/person")
    suspend fun searchPersons(
        @Query("api_key") apiKey: String = "dd6cc66d82bfef668d7eef1472ff768a",
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<PersonResponse>
}
