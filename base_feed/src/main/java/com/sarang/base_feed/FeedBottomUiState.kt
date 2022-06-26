package com.sarang.base_feed

import com.sarang.base_feed.databinding.ItemFeedTopBinding

/**
 * [ItemFeedTopBinding]
 */
data class FeedBottomUiState(
    val like: Boolean?,
    val favorite: Boolean?,
    val userName: String?,
    val contents: String?,
    val commentAmount: Int?,
    val likeAmount: Int?
)