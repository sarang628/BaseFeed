package com.sryang.base.feed.compose.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sryang.base.feed.compose.RefreshAndBottomDetectionLazyColunm
import com.sryang.base.feed.data.Review
import com.sryang.base.feed.data.testReviewData
import dagger.Binds

@Composable
fun Feeds(
    list: List<Review>,
    onProfile: ((Int) -> Unit),
    onLike: ((Int) -> Unit),
    onComment: ((Int) -> Unit),
    onShare: ((Int) -> Unit),
    onFavorite: ((Int) -> Unit),
    onMenu: ((Int) -> Unit),
    onName: ((Int) -> Unit),
    onRestaurant: ((Int) -> Unit),
    onImage: ((Int) -> Unit),
    onRefresh: (() -> Unit),
    onBottom: () -> Unit,
    isRefreshing: Boolean,
    isLoaded: Boolean,
    imageServerUrl: String,
    isVisibleList: Boolean,
    isEmpty: Boolean,
    profileImageServerUrl: String,
    ratingBar: @Composable (Float) -> Unit
)
{
    RefreshAndBottomDetectionLazyColunm( // pull to refresh와 하단 감지 적용 LazyColunm
        count = list.size,
        onBottom = onBottom,
        itemCompose = {
            Feed(review = list[it],
                onProfile = { onProfile.invoke(list[it].user.userId) },
                onLike = { onLike.invoke(list[it].reviewId) },
                onComment = { onComment.invoke(list[it].reviewId) },
                onShare = { onShare.invoke(list[it].reviewId) },
                onFavorite = { onFavorite.invoke(list[it].reviewId) },
                onMenu = { onMenu.invoke(list[it].reviewId) },
                onName = { onName.invoke(list[it].user.userId) },
                onRestaurant = { onRestaurant.invoke(list[it].restaurant.restaurantId) },
                onImage = onImage,
                imageServerUrl = imageServerUrl,
                profileImageServerUrl = profileImageServerUrl,
                ratingBar = ratingBar
            )
        },
        onRefresh = onRefresh,
        isRefreshing = isRefreshing || !isLoaded,
        visibleList = isVisibleList
    ) {
        if (isEmpty) EmptyFeed()
    }
}

@Preview
@Composable
fun PreviewFeeds()
{
    Feeds(
        list = ArrayList<Review>().apply {
            add(testReviewData())
            add(testReviewData())
            add(testReviewData())
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
        ratingBar = {},
        isEmpty = true,
        isVisibleList = true
    )
}