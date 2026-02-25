package com.sarang.torang.compose

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sarang.torang.compose.feed.FeedItem
import com.sarang.torang.compose.component.type.LocalExpandableTextType
import com.sarang.torang.compose.component.type.LocalFeedImageLoader
import com.sarang.torang.compose.component.type.LocalVideoPlayerType
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.di.basefeed_di.CustomVideoPlayerType
import kotlin.math.absoluteValue

private const val tag = "__FeedList"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedList(
    viewModel: FeedListViewModel = hiltViewModel()
) {
    val list by viewModel.feedUiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    val playingIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo

            if (visibleItems.isEmpty()) return@derivedStateOf -1

            // 화면에 가장 많이 보이는 아이템
            visibleItems.maxByOrNull { item ->
                val visibleHeight =
                    minOf(
                        item.offset + item.size,
                        layoutInfo.viewportEndOffset
                    ) - maxOf(item.offset, layoutInfo.viewportStartOffset)

                visibleHeight
            }?.index ?: -1
        }
    }

    val previousOffset = remember { mutableStateOf(0) } // 초기값 설정

    val scrollVelocity by remember {
        derivedStateOf {
            (listState.firstVisibleItemScrollOffset - previousOffset.value).absoluteValue
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                val velocity = (currentOffset - previousOffset.value).absoluteValue
                previousOffset.value = currentOffset // ✅ 여기서 안전하게 업데이트
            }
    }
    val isScrollSlow = scrollVelocity < 50

    val isScrollStopped = !listState.isScrollInProgress

    val shouldPlay = isScrollStopped || isScrollSlow


    LaunchedEffect(Unit) {
        snapshotFlow { playingIndex }.collect {
            Log.d(tag, "current play index : ${it}")
        }
    }

    CompositionLocalProvider(LocalFeedImageLoader     provides { CustomFeedImageLoader(showLog = true).invoke(it) },
        LocalExpandableTextType  provides CustomExpandableTextType,
        LocalVideoPlayerType     provides CustomVideoPlayerType()) {
        LazyColumn(state = listState) {
            itemsIndexed(list) { index, item ->
                FeedItem(uiState = item,
                         onPage = {},
                         isPlaying = shouldPlay)
            }
        }
    }
}