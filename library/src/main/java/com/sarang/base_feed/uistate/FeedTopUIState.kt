package com.sarang.base_feed.uistate


/*피드 상단 UIState*/
data class FeedTopUIState(
    val reviewId: Int,
    val userId: Int,
    val name: String,
    val restaurantName: String,
    val rating: Float,
    val profilePictureUrl: Any
)