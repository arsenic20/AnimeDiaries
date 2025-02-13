package com.example.famestream.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.famestream.model.Person
import com.example.famestream.model.PersonResponse
import retrofit2.Response

class PersonPagingSource(
    private val apiCall: suspend (Int) -> Response<PersonResponse>
) : PagingSource<Int, Person>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiCall(currentPage)
            val persons = response.body()?.results ?: emptyList()
            LoadResult.Page(
                data = persons,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (persons.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}
