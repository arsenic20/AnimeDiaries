package com.example.animediaries.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.animediaries.model.Anime
import com.example.animediaries.model.AnimeResponse
import retrofit2.Response

class AnimePagingSource(
    private val apiCall: suspend (Int) -> Response<AnimeResponse>
) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiCall(currentPage)

            if (response.isSuccessful) {
                val animeList = response.body()?.data ?: emptyList()
                val nextPage = if (response.body()?.pagination?.has_next_page == true) {
                    currentPage + 1
                } else {
                    null
                }

                LoadResult.Page(
                    data = animeList,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = nextPage
                )
            } else {
                LoadResult.Error(Exception("API call failed"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}