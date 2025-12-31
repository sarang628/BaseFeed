package com.sarang.torang.data.basefeed

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @see [/documents/UIState.md]
 *
 * @param userName          리뷰 작성자 명
 * @param profilePictureUrl 프로필 이미지 주소
 * @param restaurantName    음식점 명
 * @param rating            평점
 * @param reviewImages      리뷰 이미지
 * @param contents          리뷰 내용
 * @param comments          코멘트
 * @param likeAmount        좋아요 갯수
 * @param isLike            나의 좋아요 여부
 * @param isFavorite        나의 즐겨찾기 여부
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
    val height              : Int           = 600,
    val width               : Int           = 600,
    val createDate          : String        = "",
    val isLogin             : Boolean       = false
){ companion object }


val FeedItemUiState.adjustHeight: Dp
    @Composable get() {

        val density = LocalDensity.current

        val screenWidthPx = with(density) {
            LocalConfiguration.current.screenWidthDp.dp.roundToPx()
        }

        // 화면 가로에 맞추는 scale 비율
        val scale = screenWidthPx.toFloat() / width.toFloat()

        // 스케일링된 높이(px)
        val newHeightPx = height * scale
        val newHeightDp = with(density) { newHeightPx.toDp() }

        return newHeightDp
    }
