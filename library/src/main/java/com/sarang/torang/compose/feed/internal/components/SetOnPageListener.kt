package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import com.sarang.torang.data.basefeed.FeedItemPageEvent
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun SetOnPageListener(pagerState: PagerState, onPage : (FeedItemPageEvent) -> Unit, imageSize : Int){
    LaunchedEffect(Unit) {
        snapshotFlow{pagerState.currentPage}
            .distinctUntilChanged()
            .collect{ onPage.invoke(
                FeedItemPageEvent(page = it,
                    isFirst = it == 0,
                    isLast = it == imageSize - 1)
            )
            }
    }
}