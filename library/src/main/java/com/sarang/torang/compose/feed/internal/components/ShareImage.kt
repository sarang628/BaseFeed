package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun ShareImage(modifier: Modifier, onShare: () -> Unit) {
    Image(painter = painterResource(id = R.drawable.share),
        contentDescription = "",
        modifier = modifier
            .layoutId("share")
            .size(25.dp)
            .nonEffectclickable {
                onShare.invoke()
            })
}