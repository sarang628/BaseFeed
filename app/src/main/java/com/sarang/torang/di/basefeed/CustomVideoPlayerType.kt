package com.sarang.torang.di.basefeed

import com.sarang.torang.VideoPlayerScreen
import com.sarang.torang.compose.feed.internal.components.VideoPlayerType

val CustomVideoPlayerType: VideoPlayerType = {
    VideoPlayerScreen(videoUrl = it, isPlaying = true, onClick = {}, onPlay = {})
}