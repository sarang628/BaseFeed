package com.sarang.torang.compose.feed.internal.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

typealias FeedImageLoader = @Composable (Modifier, String, Dp?, Dp?, ContentScale?, Dp?) -> Unit

val LocalFeedImageLoader = compositionLocalOf<FeedImageLoader> {
    @Composable { _, _, _, _, _, _ -> Log.w("__LocalFeedImageLoader", "imageLoadCompose doesn't set") }
}