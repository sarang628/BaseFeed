package com.sryang.torang.compose.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sryang.torang.compose.feed.internal.EmptyFeed
import com.sryang.torang.compose.feed.internal.FeedShimmer
import com.sryang.torang.compose.feed.internal.components.RefreshAndBottomDetectionLazyColunm
import com.sryang.torang.data.basefeed.Review
import com.sryang.torang.data.basefeed.testReviewData

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
    isEmpty: Boolean,
    ratingBar: @Composable (Modifier, Float) -> Unit,
    isLoading: Boolean
) {
    var scrollEnabled by remember { mutableStateOf(false) }
    if (!isLoading) {
        RefreshAndBottomDetectionLazyColunm(
            // pull to refresh와 하단 감지 적용 LazyColunm
            count = list.size,
            onBottom = onBottom,
            itemCompose = {
                Feed(
                    review = list[it],
                    onProfile = { onProfile.invoke(list[it].user.userId) },
                    onLike = { onLike.invoke(list[it].reviewId) },
                    onComment = { onComment.invoke(list[it].reviewId) },
                    onShare = { onShare.invoke(list[it].reviewId) },
                    onFavorite = { onFavorite.invoke(list[it].reviewId) },
                    onMenu = { onMenu.invoke(list[it].reviewId) },
                    onName = { onName.invoke(list[it].user.userId) },
                    onRestaurant = { onRestaurant.invoke(list[it].restaurant.restaurantId) },
                    onImage = onImage,
                    ratingBar = ratingBar,
                    isZooming = {
                        scrollEnabled = !it
                    }
                )
            },
            onRefresh = onRefresh,
            isRefreshing = isRefreshing,
            userScrollEnabled = scrollEnabled
        ) {
            if (isEmpty) EmptyFeed()
        }
    } else {
        FeedShimmer()
    }
}

@Preview
@Composable
fun PreviewFeeds() {
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
        ratingBar = { modifier, fl -> },
        isEmpty = false,
        isLoading = false
    )
}