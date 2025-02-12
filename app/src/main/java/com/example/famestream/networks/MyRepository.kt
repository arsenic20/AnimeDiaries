package com.example.famestream.networks

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.famestream.model.Person
import com.example.famestream.paging.PersonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService) {
    fun getPopularPersons(): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                PersonPagingSource { page -> apiService.getPopularPersons(page = page) }
            }
        ).flow
    }

    fun searchPersons(query: String): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                PersonPagingSource { page -> apiService.searchPersons(query = query, page = page) }
            }
        ).flow
    }
}

