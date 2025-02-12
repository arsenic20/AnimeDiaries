package com.example.famestream.networks

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.famestream.model.Person
import com.example.famestream.paging.PersonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService) {
    fun getPopularPersonsPager(apiKey: String): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Page size from API
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PersonPagingSource(apiService, apiKey) }
        ).flow
    }
}

