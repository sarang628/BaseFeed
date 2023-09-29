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

fun testTopUiState(): FeedTopUIState {
    return FeedTopUIState(
        reviewId = 0,
        userId = 0,
        name = "name",
        restaurantName = "restaurantName",
        rating = 4.5f,
        profilePictureUrl = "1/2023-09-14/10_44_39_302.jpeg"
    )
}