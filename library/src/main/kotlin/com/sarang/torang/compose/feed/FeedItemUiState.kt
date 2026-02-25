package com.sarang.torang.compose.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.component.data.Comment

/**
 * @see [/documents/UIState.md]
 * @param feedTopUiState    피드 상단 ui state
 * @param feedTopUiState    피드 하단 ui state
 * @param reviewImages      리뷰 이미지
 * @param contents          리뷰 내용
 * @param comments          코멘트
 * @param commentAmount     코멘트 전체 갯수
 * @param height            첫번째 이미지의 높이
 * @param width             첫번째 이미지의 너비
 * @param createDate        작성일
 */
data class FeedItemUiState(
    val feedTopUiState      : FeedTopUiState    = FeedTopUiState(),
    val feedBottomUiState   : FeedBottomUiState = FeedBottomUiState(),
    val reviewImages        : List<String>      = emptyList(),
    val contents            : String            = "",
    val comments            : List<Comment>     = emptyList(),
    val commentAmount       : Int               = 0,
    val height              : Int               = 600,
    val width               : Int               = 600,
    val createDate          : String            = "",
    val isPlay              : Boolean           = true
){ companion object }

val FeedItemUiState.isVideo: Boolean
    get() = reviewImages.firstOrNull()
                        ?.substringAfterLast('.', "")
                        ?.equals("m3u8", ignoreCase = true) == true


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


/**
 * @param userName          리뷰 작성자 명
 * @param profilePictureUrl 프로필 이미지 주소
 * @param restaurantName    음식점 명
 * @param rating            평점
 */
data class FeedTopUiState(
    val profilePictureUrl   : String = "",
    val userName            : String = "",
    val restaurantName      : String = "",
    val rating              : Float = 0.0f
)

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