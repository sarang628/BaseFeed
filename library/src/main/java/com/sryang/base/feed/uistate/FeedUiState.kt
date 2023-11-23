package com.sryang.base.feed.uistate

import com.sryang.base.feed.data.Review
import com.sryang.base.feed.data.testReviewList

/*피드 UIState*/
data class FeedUiState(
    val reviews: List<Review>,  // 리뷰 리스트
    val isLoaded: Boolean,      // 최초 리스트 데이터 로드되었는지?
    val isRefreshing: Boolean   // 갱신중인지 여부
)

// 리스트 표시 여부
val FeedUiState.isVisibleList: Boolean get() = isLoaded && !reviews.isEmpty()

// 리스트가 비어있는지 여부
val FeedUiState.isEmpty: Boolean get() = isLoaded && reviews.isEmpty()

fun testFeedUiState(): FeedUiState {
    return FeedUiState(
        reviews = testReviewList(),
        isLoaded = true,
        isRefreshing = false
    )
}