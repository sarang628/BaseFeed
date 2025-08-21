package com.sarang.torang.data.basefeed

/**
 * @see [/documents/UIState.md]
 *
 * @param userName          사용자 정보
 * @param profilePictureUrl 프로필 url
 * @param restaurantName    음식점 명
 * @param rating            평점
 * @param reviewImages      리뷰 이미지
 * @param contents          내용
 * @param comments          코멘트
 * @param likeAmount        좋아요 갯수
 * @param isLike            사용자의 좋아요 여부
 * @param isFavorite        사용자의 즐겨찾기 여부
 * @param createDate        생성일
 */
data class FeedItemUiState(
    val userName            : String        = "",
    val profilePictureUrl   : String        = "",
    val restaurantName      : String        = "",
    val rating              : Float         = 0f,
    val reviewImages        : List<String>  = listOf(),
    val contents            : String        = "",
    val comments            : List<Comment> = listOf(),
    val likeAmount          : Int           = 0,
    val commentAmount       : Int           = 0,
    val isLike              : Boolean       = false,
    val isFavorite          : Boolean       = false,
    val createDate          : String        = ""
){ companion object }