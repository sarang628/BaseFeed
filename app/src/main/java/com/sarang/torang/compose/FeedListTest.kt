package com.sarang.torang.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sarang.torang.compose.feed.FeedItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedList(
    viewModel: FeedListViewModel = hiltViewModel()
) {
    val list by viewModel.feedUiState.collectAsStateWithLifecycle()
    LazyColumn {
        items(list) {
            FeedItem(uiState = it, onPage = {})
        }
    }
}