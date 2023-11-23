package com.sryang.base.feed.compose.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sryang.base.feed.compose.RefreshAndBottomDetectionLazyColunm
import com.sryang.base.feed.uistate.FeedUiState
import com.sryang.base.feed.uistate.testFeedUiState

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
    onRestaurant: ((Int) -> Unit),
    onImage: ((Int) -> Unit),
    onRefresh: (() -> Unit),
    onBottom: () -> Unit,
    isRefreshing: Boolean,
    isLoaded: Boolean,
    imageServerUrl: String,
    profileImageServerUrl: String,
    ratingBar: @Composable (Float) -> Unit
) {
    RefreshAndBottomDetectionLazyColunm( // pull to refresh와 하단 감지 적용 LazyColunm
        count = list.size,
        onBottom = onBottom,
        itemCompose = {
            ItemFeed(
                list[it],
                onProfile = { onProfile.invoke(list[it].userId) },
                onLike = { onLike.invoke(list[it].reviewId) },
                onComment = { onComment.invoke(list[it].reviewId) },
                onShare = { onShare.invoke(list[it].reviewId) },
                onFavorite = { onFavorite.invoke(list[it].reviewId) },
                onMenu = onMenu,
                onName = onName,
                onRestaurant = { onRestaurant.invoke(list[it].restaurantId) },
                onImage = onImage,
                imageServerUrl = imageServerUrl,
                profileImageServerUrl = profileImageServerUrl,
                ratingBar = ratingBar
            )
        },
        onRefresh = onRefresh,
        isRefreshing = isRefreshing || !isLoaded
    ) {
        if (isLoaded && list.isEmpty())
            EmptyFeed()
    }
}

@Preview
@Composable
fun PreviewFeeds() {
    Feeds(
        list = ArrayList<FeedUiState>().apply {
            add(testFeedUiState())
            add(testFeedUiState())
            add(testFeedUiState())
        },
        onProfile = {},
        onLike = {},
        onComment = {},
        onShare = {},
        onFavorite = {},
        onMenu = { /*TODO*/ },
        onName = { /*TODO*/ },
        onRestaurant = {},
        onImage = {},
        onRefresh = { /*TODO*/ },
        onBottom = { /*TODO*/ },
        isRefreshing = false,
        isLoaded = false,
        imageServerUrl = "",
        profileImageServerUrl = "",
        ratingBar = {}
    )
}