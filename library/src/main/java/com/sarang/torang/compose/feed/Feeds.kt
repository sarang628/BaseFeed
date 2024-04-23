package com.sarang.torang.compose.feed

import android.content.res.Configuration
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    feedsUiState: FeedsUiState,
    progressTintColor: Color? = null
) {
    var scrollEnabled by remember { mutableStateOf(true) }

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
                        isZooming = {
                            scrollEnabled = !it
                        },
                        progressTintColor = progressTintColor
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

@Composable
fun PreviewFeeds() {
    Feeds(/* Preview */
        onRefresh = { /*TODO*/ },
        onBottom = { /*TODO*/ },
        isRefreshing = false,
        //feedsUiState = FeedsUiState.Loading
        feedsUiState = FeedsUiState.Success(ArrayList<Review>().apply {
            add(testReviewData())
            add(testReviewData())
            add(testReviewData())
        })
    )
}