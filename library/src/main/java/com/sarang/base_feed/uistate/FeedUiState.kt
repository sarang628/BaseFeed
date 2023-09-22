package com.sarang.base_feed.uistate

/*피드 UIState*/
data class FeedUiState(
    val reviewId: Int,
    val itemFeedTopUiState: FeedTopUIState,
    val itemFeedBottomUiState: FeedBottomUIState,
    val reviewImages: List<String> = ArrayList(),
    )