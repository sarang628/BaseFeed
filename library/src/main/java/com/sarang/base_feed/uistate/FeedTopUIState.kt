package com.sarang.base_feed.uistate



/*피드 상단 UIState*/
data class FeedTopUIState(
    val reviewId: Int? = 0,
    val userId: Int? = 0,
    val name: String? = null,
    val restaurantName: String? = null,
    val rating: Float? = null,
    val profilePictureUrl: Any? = null
)