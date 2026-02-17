package com.sarang.torang.compose.feed.internal.components

/**
 * @param likeAmount        좋아요 갯수
 * @param isLike            나의 좋아요 여부
 * @param isFavorite        나의 즐겨찾기 여부
 */
data class FeedBottomUiState(
    val isLike                : Boolean              = false,
    val isLogin               : Boolean              = false,
    val isFavorite            : Boolean              = false,
    val isVolumeOff           : Boolean              = false,
    val likeAmount            : Int                  = 0,
)