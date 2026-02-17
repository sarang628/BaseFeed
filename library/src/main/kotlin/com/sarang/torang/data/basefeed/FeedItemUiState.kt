package com.sarang.torang.data.basefeed

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.components.FeedBottomUiState
import com.sarang.torang.compose.feed.internal.components.FeedTopUiState

/**
 * @see [/documents/UIState.md]
 * @param reviewImages      리뷰 이미지
 * @param contents          리뷰 내용
 * @param comments          코멘트
 * @param createDate        생성일
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
