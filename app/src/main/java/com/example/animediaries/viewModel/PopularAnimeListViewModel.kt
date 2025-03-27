package com.example.animediaries.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.animediaries.networks.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularAnimeListViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    val pagedAnime = repository.getPopularAnime()
        .cachedIn(viewModelScope)
}