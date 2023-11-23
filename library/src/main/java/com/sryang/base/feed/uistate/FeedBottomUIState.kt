package com.sryang.base.feed.uistate


/*피드 하단 UIState*/
data class FeedBottomUIState(
    val likeAmount: Int,
    val commentAmount: Int,
    val author: String,
    val author1: String,
    val author2: String,
    val comment: String,
    val comment1: String,
    val comment2: String,
    val isLike: Boolean,
    val isFavorite: Boolean,
    val contents: String
)


fun testFeedBottomUiState(): FeedBottomUIState {
    return FeedBottomUIState(
        likeAmount = 0,
        commentAmount = 0,
        author = "author",
        author1 = "author1",
        author2 = "author2",
        comment = "comment",
        comment1 = "comment1",
        comment2 = "comment2",
        isLike = false,
        isFavorite = false,
        contents = "contents"
    )
}