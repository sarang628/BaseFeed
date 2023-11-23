package com.sarang.base_feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sryang.library.BottomDetectingLazyColumn

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefreshAndBottomDetectionLazyColunm(
    count: Int,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onBottom: () -> Unit,
    itemCompose: @Composable (Int) -> Unit,
    contents: @Composable (() -> Unit)? = null
) {

    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    val mod2 = Modifier
        .fillMaxSize()
        .pullRefresh(pullRefreshState)

    Box(
        modifier = mod2
    ) {
        BottomDetectingLazyColumn(
            items = count,
            onBottom = { onBottom.invoke() },
            composable = { itemCompose.invoke(it) }
        )

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        contents?.invoke()
    }
}