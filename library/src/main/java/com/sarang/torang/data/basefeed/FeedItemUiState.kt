package com.sarang.torang.data.basefeed

/**
 * https://developer.android.com/topic/architecture/ui-layer#define-ui-state
 * UI는 사용자에게 보여주는 시각적 표시라면 UI state는 이 정보를 담고 있다.
 * UI는 사용자가 보는 것 이라면, UI state는 앱이 사용자이 봐야하는 것을 말하는 것이다.
 * UI는 UIstate의 시각젹 표시이며, UIstate가 변경 시 UI에 즉시 반영 되어야 한다.
 *
 * UIstate 만 봐도 어떤 정보들이 화면에 표시되는지 할 수 있어야할 것 같다.
 *
 * @param user          사용자 정보
 * @param restaurant    음식점 정보
 * @param rating        평점
 * @param reviewImages  리뷰 이미지
 * @param contents      내용
 * @param comments      코멘트
 * @param likeAmount    좋아요 갯수
 * @param isLike        사용자의 좋아요 여부
 * @param isFavorite    사용자의 즐겨찾기 여부
 * @param createDate    생성일
 */
data class FeedItemUiState(
    val user            : User,
    val restaurant      : Restaurant,
    val rating          : Float,
    val reviewImages    : List<String> = ArrayList(),
    val contents        : String,
    val comments        : List<Comment>?,
    val likeAmount      : Int,
    val commentAmount   : Int,
    val isLike          : Boolean,
    val isFavorite      : Boolean,
    val createDate      : String
){ companion object }