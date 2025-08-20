package com.sarang.torang.compose.feed.internal.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

typealias VideoPlayerType = @Composable (String) -> Unit

val LocalVideoPlayerType = compositionLocalOf<VideoPlayerType> {
    @Composable {

    }
}