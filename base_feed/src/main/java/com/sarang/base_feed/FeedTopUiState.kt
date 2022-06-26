package com.sarang.base_feed

import com.sarang.base_feed.databinding.ItemFeedTopBinding

/**
 * [ItemFeedTopBinding]
 */
data class FeedTopUiState(
    val profileImageUrl: String?,
    val userName: String?,
    val restaurantName: String?,
    val rating: Float?,
    val clickProfileImage: ((Int) -> Unit)?,
    val clickUserName: ((Int) -> Unit)?,
    val clickRestaurant: ((Int) -> Unit)?
)