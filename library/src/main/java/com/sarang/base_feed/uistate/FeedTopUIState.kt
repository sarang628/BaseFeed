package com.sarang.base_feed.uistate


/*피드 상단 UIState*/
data class FeedTopUIState(
    val name: String,
    val restaurantName: String,
    val rating: Float,
    val profilePictureUrl: String
)

fun testTopUiState(): FeedTopUIState {
    return FeedTopUIState(
        name = "name",
        restaurantName = "restaurantName",
        rating = 4.5f,
        profilePictureUrl = "1/2023-10-14/08_48_11_972.jpg",
    )
}