package com.example.animediaries.networks

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.animediaries.model.Anime
import com.example.animediaries.model.AnimeDetailResponse
import com.example.animediaries.paging.AnimePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val apiService: ApiService) {
    fun getPopularAnime(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false,
                initialLoadSize = 25
            ),
            pagingSourceFactory = {
                AnimePagingSource { page ->
                    apiService.getAnimeData(page = page)
                }
            }
        ).flow
    }

    suspend fun getAnimeDetails(animeId: Int): AnimeDetailResponse? {
        return apiService.getAnimeDetails(animeId.toString()).body()
    }
}

