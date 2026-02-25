package com.sarang.torang.compose.feed.data

import androidx.compose.ui.graphics.Color

/**
 * @param ratingBarColor     ratingbar 색상
 * @param favoriteColor      favorite 색상
 */
data class FeedItemColors(
    val ratingBarColor : Color = Color(0xffe6cc00),
    val favoriteColor  : Color = Color(0xffe6cc00),
)