package com.sarang.torang.compose.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sarang.torang.compose.feed.internal.EmptyFeed
import com.sarang.torang.compose.feed.internal.FeedShimmer
import com.sarang.torang.compose.feed.internal.components.RefreshAndBottomDetectionLazyColunm
import com.sarang.torang.data.basefeed.Review
import com.sarang.torang.data.basefeed.testReviewData
import com.sarang.torang.uistate.FeedsUiState

@Composable
fun Feeds(
    onRefresh: (() -> Unit),
    onBottom: () -> Unit,
    isRefreshing: Boolean,
    ratingBar: @Composable (Modifier, Float) -> Unit,
    feedsUiState: FeedsUiState
) {
    var scrollEnabled by remember { mutableStateOf(false) }

    when (feedsUiState) {
        is FeedsUiState.Loading -> {
            FeedShimmer()
        }

        is FeedsUiState.Empty -> {
            RefreshAndBottomDetectionLazyColunm(
                // pull to refresh와 하단 감지 적용 LazyColunm
                count = 0,
                onBottom = {},
                itemCompose = {},
                onRefresh = onRefresh,
                isRefreshing = isRefreshing,
                userScrollEnabled = scrollEnabled
            ) {
                EmptyFeed()
            }
        }

        is FeedsUiState.Success -> {
            RefreshAndBottomDetectionLazyColunm(
                // pull to refresh와 하단 감지 적용 LazyColunm
                count = feedsUiState.reviews.size,
                onBottom = onBottom,
                itemCompose = {
                    Feed(
                        review = feedsUiState.reviews[it],
                        ratingBar = ratingBar,
                        isZooming = {
                            scrollEnabled = !it
                        }
                    )
                },
                onRefresh = onRefresh,
                isRefreshing = isRefreshing,
                userScrollEnabled = scrollEnabled
            )
        }

        is FeedsUiState.Error -> {}
    }
}

@Preview
@Composable
fun PreviewFeeds() {
    Feeds(/* Preview */
        onRefresh = { /*TODO*/ },
        onBottom = { /*TODO*/ },
        isRefreshing = false,
        ratingBar = { modifier, fl -> },
        //feedsUiState = FeedsUiState.Loading
        feedsUiState = FeedsUiState.Success(ArrayList<Review>().apply {
            add(testReviewData())
            add(testReviewData())
            add(testReviewData())
        })
    )
}