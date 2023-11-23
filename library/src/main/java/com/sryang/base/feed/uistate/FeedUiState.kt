package com.sryang.base.feed.uistate

import com.sryang.base.feed.data.Review
import com.sryang.base.feed.data.testReviewData

/*피드 UIState*/
data class FeedUiState(
    val review: Review,
)

fun testFeedUiState(): FeedUiState {
    return FeedUiState(
        review = testReviewData()
    )
}