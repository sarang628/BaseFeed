package com.sarang.base_feed.uistate

import com.sryang.library.entity.Feed

/*피드 UIState*/
data class FeedUiState(
    val reviewId: Int? = 0,
    val itemFeedTopUiState: FeedTopUIState,
    val itemFeedBottomUiState: FeedBottomUIState,
    val reviewImages: List<String>? = ArrayList(),
    val visibleReviewImage: Boolean = false,
    val imageClickListener: ((Int) -> Unit)? = null,

    )

fun Feed.FeedUiState(): FeedUiState {
    return FeedUiState(
        reviewId = this.reviewId,
        itemFeedTopUiState = this.FeedTopUiState(),
        itemFeedBottomUiState = this.FeedBottomUIState(),
        reviewImages = this.reviewImages,
        visibleReviewImage = true
    )
}