package com.sarang.torang.compose.feed.internal.components

/**
 * @param userName          리뷰 작성자 명
 * @param profilePictureUrl 프로필 이미지 주소
 * @param restaurantName    음식점 명
 * @param rating            평점
 */
data class FeedTopUiState(
    val profilePictureUrl : String = "",
    val userName : String = "",
    val restaurantName : String = "",
    val rating : Float = 0.0f
)