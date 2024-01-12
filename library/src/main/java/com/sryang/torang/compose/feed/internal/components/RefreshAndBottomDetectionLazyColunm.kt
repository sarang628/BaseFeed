package com.sryang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import com.sryang.library.BottomDetectingLazyColumn
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.RefreshIndicatorState
import com.sryang.library.pullrefresh.rememberPullToRefreshState

@Composable
internal fun RefreshAndBottomDetectionLazyColunm(
    count: Int,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onBottom: () -> Unit,
    userScrollEnabled: Boolean = true,
    itemCompose: @Composable (Int) -> Unit,
    contents: @Composable (() -> Unit)? = null
) {

    //val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)

    val state = rememberPullToRefreshState()
    LaunchedEffect(key1 = isRefreshing, block = {
        if (isRefreshing) {
            state.updateState(RefreshIndicatorState.Refreshing)
        } else {
            state.updateState(RefreshIndicatorState.Default)
        }
    })

    PullToRefreshLayout(
        pullRefreshLayoutState = state,
        refreshThreshold = 80,
        onRefresh = onRefresh
    ) {
        BottomDetectingLazyColumn(
            items = count,
            onBottom = { onBottom.invoke() },
            composable = { itemCompose.invoke(it) },
            userScrollEnabled = userScrollEnabled,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        )
        contents?.invoke()
    }
}