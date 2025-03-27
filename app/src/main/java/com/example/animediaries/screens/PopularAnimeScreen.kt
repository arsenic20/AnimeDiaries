package com.example.animediaries.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animediaries.viewModel.PopularAnimeListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularAnimeScreen(onNavigateDetail: (Int) -> Unit) {
    val viewModel = hiltViewModel<PopularAnimeListViewModel>()
    val animeItems = viewModel.pagedAnime.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime's", color = Color.Black) },
                colors = TopAppBarColors(Color.Gray, Color.Gray, Color.Gray, Color.Gray, Color.Gray)
            )

        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(animeItems.itemCount) { index ->
                    val anime = animeItems[index]
                    anime?.let {
                        AnimeItem(anime, onNavigateDetail)
                    }
                }

                animeItems.apply {
                    when {
                        loadState.append is LoadState.Loading -> {
                            item { Loader() }
                        }

                        loadState.refresh is LoadState.Error -> {
                            item {
                                ErrorItem(
                                    message = "Error loading data",
                                    modifier = Modifier.fillParentMaxSize(),
                                    onClickRetry = { retry() }
                                )
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            item {
                                ErrorItem(
                                    message = "Error loading more data",
                                    onClickRetry = { retry() }
                                )
                            }
                        }
                    }
                }
            }
            if (animeItems.loadState.refresh is LoadState.Loading) Loader()
        }
    }
}

