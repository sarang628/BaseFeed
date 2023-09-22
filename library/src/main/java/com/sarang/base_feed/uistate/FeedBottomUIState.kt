package com.sarang.base_feed.uistate



/*피드 하단 UIState*/
data class FeedBottomUIState(
    val reviewId: Int,
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
    val visibleLike: Boolean,
    val visibleComment: Boolean,
    val contents: String
)
