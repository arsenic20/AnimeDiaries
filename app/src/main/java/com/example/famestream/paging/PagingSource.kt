package com.example.famestream.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.famestream.model.Person
import com.example.famestream.networks.ApiService

class PersonPagingSource (
    private val apiService: ApiService,
    private val apiKey: String
) : PagingSource<Int, Person>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        val page = params.key ?: 1 // Start from page 1 if not specified
        return try {

            val response = apiService.getPopularPersons(apiKey, page)
            val persons = response.body()?.results ?: emptyList()

            LoadResult.Page(
                data = persons,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (persons.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
