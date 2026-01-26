package com.sarang.torang.compose.feed.internal.components.type

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

data class VideoPlayerTypeData(val url          : String    = "",
                               val isPlaying    : Boolean   = false)

typealias VideoPlayerType = @Composable (VideoPlayerTypeData) -> Unit

val LocalVideoPlayerType = compositionLocalOf<VideoPlayerType> {
    @Composable {

    }
}