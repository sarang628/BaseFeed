package com.sarang.torang.compose.feed.internal.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.R


data class FeedImageLoaderData(
    val modifier        : Modifier = Modifier,
    val url             : String? = null,
    val progressSize    : Dp = 30.dp,
    val errorIconSize   : Dp = 30.dp,
    val contentScale    : ContentScale = ContentScale.Fit,
    val height          : Dp = 30.dp
)

typealias FeedImageLoader = @Composable (FeedImageLoaderData) -> Unit

val LocalFeedImageLoader = compositionLocalOf<FeedImageLoader> {
    { data ->
        Log.w("__LocalFeedImageLoader", "imageLoadCompose doesn't set")
        Image(
            modifier = data.modifier,
            painter = painterResource(R.drawable.sample),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

