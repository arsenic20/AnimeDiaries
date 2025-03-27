package com.example.animediaries.networks

import com.example.animediaries.model.AnimeDetailResponse
import com.example.animediaries.model.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v4/top/anime")
    suspend fun getAnimeData(
        @Query("page") page: Int // Add this for pagination
    ): Response<AnimeResponse>

    @GET("v4/anime/{anime_id}")
    suspend fun getAnimeDetails(
        @Path("anime_id") animeId: String,
    ): Response<AnimeDetailResponse>
}
