package com.sarang.base_feed.uistate

import com.sryang.library.entity.Feed

/*피드 상단 UIState*/
data class FeedTopUIState(
    val reviewId: Int? = 0,
    val userId: Int? = 0,
    val name: String? = null,
    val restaurantName: String? = null,
    val rating: Float? = null,
    val profilePictureUrl: Any? = null
)

fun Feed.FeedTopUiState(): FeedTopUIState {
    return FeedTopUIState(
        reviewId = this.reviewId,
        userId = this.writer.userId,
        name = this.writer.name,
        restaurantName = this.restaurant.restaurantName,
        rating = this.rating,
        profilePictureUrl = this.writer.profilePictureUrl
    )
}