package com.sarang.base_feed.uistate

/*피드 UIState*/
data class FeedUiState(
    val reviewId: Int,
    val itemFeedTopUiState: FeedTopUIState,
    val itemFeedBottomUiState: FeedBottomUIState,
    val reviewImages: List<String> = ArrayList(),
)

fun testFeedUiState(): FeedUiState {
    return FeedUiState(
        reviewId = 0,
        itemFeedTopUiState = testTopUiState(),
        itemFeedBottomUiState = testFeedBottomUiState(),
        reviewImages = ArrayList<String>().apply {
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
        }
    )
}