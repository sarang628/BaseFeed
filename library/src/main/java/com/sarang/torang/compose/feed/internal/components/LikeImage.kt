package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun LikeImage(
    modifier: Modifier,
    isLike: Boolean,
    onLike: () -> Unit,
    size: Dp,
    padding: Dp
) {
    Icon(
        imageVector = if (isLike) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = "like",
        modifier = modifier
            .size(size)
            .padding(padding)
            .nonEffectclickable {
                onLike.invoke()
            }
    )
}