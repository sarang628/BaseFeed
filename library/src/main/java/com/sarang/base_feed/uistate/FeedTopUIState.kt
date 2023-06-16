package com.sarang.base_feed.uistate

import com.example.screen_feed.data.Feed

/*피드 상단 UIState*/
data class FeedTopUIState(
    val reviewId: Int? = 0,
    val name: String? = null,
    val restaurantName: String? = null,
    val rating: Float? = null,
    val profilePictureUrl: Any? = null
)

fun Feed.FeedTopUiState(): FeedTopUIState {
    return FeedTopUIState(
        reviewId = this.reviewId,
        name = this.name,
        restaurantName = this.restaurantName,
        rating = this.rating,
        profilePictureUrl = this.profilePictureUrl
    )
}