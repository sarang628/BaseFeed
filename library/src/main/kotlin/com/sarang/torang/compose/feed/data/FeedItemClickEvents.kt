package com.sarang.torang.compose.feed.data

import android.util.Log

/**
 * @param onImage           이미지 클릭
 * @param onLikes           좋아요 클릭
 */
data class FeedItemClickEvents (
    val tag                 : String                    = "__FeedItemClickEvents",
    val onLikes             : () -> Unit                = { Log.w(tag, "onLikes callback is not set") },
    val onImage             : (Int) -> Unit             = { Log.w(tag, "onImage callback is not set") },
    val feedTopEvents       : FeedTopEvents             = FeedTopEvents(),
    val feedBottomEvents    : FeedBottomEvents          = FeedBottomEvents()
)

/**
 * @param onName            사용자명 클릭
 * @param onRestaurant      음식점명 클릭
 * @param onMenu            메뉴 클릭
 * @param onProfile         프로필 클릭
 */
data class FeedTopEvents(
    val tag                 : String                    = "__FeedTopEvents",
    val onName              : () -> Unit                = { Log.w(tag, "onName callback is not set") },
    val onProfile           : () -> Unit                = { Log.w(tag, "onProfile callback is not set") },
    val onRestaurant        : () -> Unit                = { Log.w(tag, "onRestaurant callback is not set") },
    val onMenu              : () -> Unit                = { Log.w(tag, "onMenu callback is not set") },
)

/**
 * @param onFavorite        즐겨찾기 클릭
 * @param onShare           공유 클릭
 * @param onComment         코멘트 클릭
 * @param onLike            좋아요 클릭
 */
data class FeedBottomEvents(
    val tag                 : String                    = "__FeedBottomEvents",
    val onLike              : () -> Unit                = { Log.w(tag, "onLike callback is not set") },
    val onShare             : () -> Unit                = { Log.w(tag, "onShare callback is not set") },
    val onComment           : () -> Unit                = { Log.w(tag, "onComment callback is not set") },
    val onFavorite          : () -> Unit                = { Log.w(tag, "onFavorite callback is not set") },
    val onVolume            : () -> Unit                = { Log.w(tag, "onVolume callback is not set") },
)