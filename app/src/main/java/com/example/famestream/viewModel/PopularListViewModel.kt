package com.example.famestream.viewModel

import androidx.lifecycle.ViewModel
import com.example.famestream.networks.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularListViewModel @Inject constructor(private val myRepository: MyRepository) :
    ViewModel() {

}