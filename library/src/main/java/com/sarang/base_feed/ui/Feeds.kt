package com.sarang.base_feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sarang.base_feed.ui.itemfeed.ItemFeed
import com.sarang.base_feed.uistate.FeedUiState
import com.sryang.library.BottomDetectingLazyColumn
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefreshAndBottomDetectionLazyColunm(
    count: Int,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onBottom: ((Void?) -> Unit),
    itemCompose: @Composable (Int) -> Unit
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
            onBottom = onBottom,
            composable = { itemCompose.invoke(it) }
        )

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun Feeds(
    list: ArrayList<FeedUiState>,
    onProfile: ((Int) -> Unit),
    onLike: ((Int) -> Unit),
    onComment: ((Int) -> Unit),
    onShare: ((Int) -> Unit),
    onFavorite: ((Int) -> Unit),
    onMenu: (() -> Unit),
    onName: (() -> Unit),
    onRestaurant: (() -> Unit),
    onImage: ((Int) -> Unit),
    onRefresh: (() -> Unit),
    isRefreshing: Boolean,
    imageServerUrl: String = "",
    profileImageServerUrl: String = ""
) {
    RefreshAndBottomDetectionLazyColunm(
        count = list.size,
        onBottom = {},
        itemCompose = {
            ItemFeed(
                list[it],
                onProfile = onProfile,
                onLike = onLike,
                onComment = onComment,
                onShare = onShare,
                onFavorite = onFavorite,
                onMenu = onMenu,
                onName = onName,
                onRestaurant = onRestaurant,
                onImage = onImage,
                imageServerUrl = imageServerUrl,
                profileImageServerUrl = profileImageServerUrl
            )
        },
        onRefresh = onRefresh,
        isRefreshing = isRefreshing
    )
}