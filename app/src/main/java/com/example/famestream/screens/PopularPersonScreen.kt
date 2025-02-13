package com.example.famestream.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.famestream.R
import com.example.famestream.model.Person
import com.example.famestream.viewModel.PopularListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularPersonsScreen(onNavigateDetail: (Person) -> Unit) {
    val viewModel = hiltViewModel<PopularListViewModel>()
    val searchQuery = viewModel.searchQuery.collectAsState()
    val persons = viewModel.pagedPersons.collectAsLazyPagingItems()
    // Tracks the state of the SearchBar
    var isSearchBarActive by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.celebrities),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 24.sp,
                    )
                },
                colors = TopAppBarColors(Color.Gray, Color.Gray, Color.Gray, Color.Gray, Color.Gray)
            )
        }

    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // SearchBar
            SearchBar(
                query = searchQuery.value,
                onQueryChange = { viewModel.onSearchQueryChanged(it) },
                onSearch = {
                    isSearchBarActive = false
                }, // Closes the SearchBar when the search is triggered
                active = isSearchBarActive,
                onActiveChange = { isSearchBarActive = it }, // Toggles SearchBar state
                placeholder = { Text(stringResource(R.string.search_person)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .wrapContentHeight(),
                trailingIcon = {
                    IconButton(onClick = { isSearchBarActive = false }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search)
                        )
                    }
                }
            ) {
                Text(stringResource(R.string.search_by_name))
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (persons.loadState.refresh != LoadState.Loading && persons.itemCount == 0)
                    LoadingFailure(stringResource(R.string.no_data_found))
                else
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(persons.itemCount) { index ->
                            val person = persons[index]
                            person?.let {
                                PersonItem(person, onNavigateDetail)
                            }
                        }

                        persons.apply {
                            when (loadState.append) {
                                is LoadState.Loading -> {
                                    item {
                                        Loader()
                                    }
                                }

                                is LoadState.Error -> {
                                    item {
                                        LoadingFailure(stringResource(R.string.error_loading))
                                    }
                                }

                                else -> {}
                            }
                        }
                    }
                if (persons.loadState.refresh is LoadState.Loading) Loader()
            }
        }
    }
}
