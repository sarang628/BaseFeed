package com.sarang.base_feed.uistate



/*피드 하단 UIState*/
data class FeedBottomUIState(
    val reviewId: Int,
    val likeAmount: Int? = 0,
    val commentAmount: Int? = 0,
    val author: String? = "",
    val author1: String? = "",
    val author2: String? = "",
    val comment: String? = "",
    val comment1: String? = "",
    val comment2: String? = "",
    val isLike: Boolean? = false,
    val isFavorite: Boolean? = false,
    val visibleLike: Boolean? = false,
    val visibleComment: Boolean? = false,
    val contents: String? = ""
)
