package com.sarang.torang.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sarang.torang.compose.feed.FeedItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedList(viewModel: FeedListViewModel = hiltViewModel(), sheetContent: @Composable ColumnScope.() -> Unit) {
    val list by viewModel.feedUiState.collectAsStateWithLifecycle()

    BottomSheetScaffold(sheetContent = sheetContent) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(list.size) {
                    FeedItem(uiState = list[it], pageScrollAble = false)
                }
            }
        }
    }
}