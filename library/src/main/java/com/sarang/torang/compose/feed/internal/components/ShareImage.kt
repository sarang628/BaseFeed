package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun ShareImage(modifier: Modifier, onShare: () -> Unit, size : Dp,
               padding : Dp
) {
    Icon(imageVector = Icons.Default.Share,
        contentDescription = "share",
        modifier = modifier
            .layoutId("share")
            .size(size)
            .padding(padding)
            .nonEffectclickable {
                onShare.invoke()
            })
}