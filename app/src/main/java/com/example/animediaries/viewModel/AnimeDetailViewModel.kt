package com.example.animediaries.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animediaries.model.AnimeDetail
import com.example.animediaries.networks.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeDetail?>(null)
    val animeDetail: StateFlow<AnimeDetail?> = _animeDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = animeRepository.getAnimeDetails(animeId)
                _animeDetail.value = response?.data
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to fetch anime details"
            } finally {
                _isLoading.value = false
            }
        }
    }
}