package com.example.famestream.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.famestream.networks.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularListViewModel @Inject constructor(private val repository: MyRepository) :
    ViewModel() {
    val personsPager = repository.getPopularPersonsPager(apiKey = "dd6cc66d82bfef668d7eef1472ff768a")
        .cachedIn(viewModelScope)

}