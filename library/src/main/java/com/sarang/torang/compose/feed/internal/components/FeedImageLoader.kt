package com.sarang.torang.compose.feed.internal.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.sarang.torang.R

typealias FeedImageLoader = @Composable (Modifier, String, Dp?, Dp?, ContentScale?, Dp?) -> Unit

val LocalFeedImageLoader = compositionLocalOf<FeedImageLoader> {
    @Composable { modifier, _, _, _, _, _ ->
        Log.w("__LocalFeedImageLoader", "imageLoadCompose doesn't set")
        Image(modifier = modifier, painter = painterResource(R.drawable.sample), contentDescription = "", contentScale = ContentScale.Crop)
    }
}