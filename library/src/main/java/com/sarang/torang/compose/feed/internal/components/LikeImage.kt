package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun LikeImage(
    modifier: Modifier,
    isLike: Boolean,
    onLike: () -> Unit
) {
    Image(
        painter = if (isLike) painterResource(id = R.drawable.heart_fill)
        else painterResource(id = R.drawable.heart),
        contentDescription = "",
        modifier = modifier
            .size(25.dp)
            .nonEffectclickable {
                onLike.invoke()
            }
    )
}