package com.example.screen_feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.library.JsonToObjectGenerator
import com.sarang.base_feed.ui.itemfeed.ItemFeed
import com.sarang.base_feed.uistate.FeedUiState
import com.sryang.library.BottomDetectingLazyColumn

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Feeds(
    feeds: List<FeedUiState>,
    isRefreshing: Boolean? = null,
    onRefresh: () -> Unit,
    onProfile: (Int) -> Unit,
    onImage: (Int) -> Unit,
    onMenu: () -> Unit,
    onName: () -> Unit,
    onRestaurant: () -> Unit,
    onLike: (Int) -> Unit,
    onComment: (Int) -> Unit,
    onShare: (Int) -> Unit,
    onFavorite: (Int) -> Unit,
    onBottom: ((Void?) -> Unit)? = null,
    imageServerUrl: String = "",
    profileImageServerUrl: String = ""
) {

    val pullRefreshState = rememberPullRefreshState(isRefreshing ?: false, onRefresh ?: { })
    val mod1 = Modifier
        .fillMaxSize()
        .pullRefresh(pullRefreshState)
        .verticalScroll(rememberScrollState())
    val mod2 = Modifier
        .fillMaxSize()
        .pullRefresh(pullRefreshState)

    Box(
        modifier = mod2
    ) {
        BottomDetectingLazyColumn(
            items = feeds.size,
            onBottom = onBottom,
            composable = {
                ItemFeed(
                    feeds[it],
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
            }
        )

        PullRefreshIndicator(
            refreshing = isRefreshing ?: false,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
fun PreviewFeeds() {
    val list = JsonToObjectGenerator<FeedUiState>().getListByFile(
        LocalContext.current,
        "feeds.json",
        FeedUiState::class.java
    )
//    Feeds(feeds = list, onBottom = {})
}