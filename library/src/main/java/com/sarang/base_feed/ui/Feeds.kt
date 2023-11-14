package com.sarang.base_feed.ui

import androidx.compose.runtime.Composable
import com.sarang.base_feed.ui.itemfeed.ItemFeed
import com.sarang.base_feed.uistate.FeedUiState

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
    imageServerUrl: String = "",
    profileImageServerUrl: String = "",
    ratingBar: @Composable (Float) -> Unit
) {
    RefreshAndBottomDetectionLazyColunm( // pull to refresh와 하단 감지 적용 LazyColunm
        count = list.size,
        onBottom = onBottom,
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
                profileImageServerUrl = profileImageServerUrl,
                ratingBar = ratingBar
            )
        },
        onRefresh = onRefresh,
        isRefreshing = isRefreshing
    )
}