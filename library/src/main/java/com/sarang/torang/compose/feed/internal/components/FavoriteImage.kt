package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun FavoriteImage(
    modifier: Modifier, isFavorite: Boolean, onFavorite: () -> Unit, size: Dp,
    padding: Dp
) {
    Icon(
        imageVector = if (isFavorite) Icons.Default.Star else Icons.Outlined.Star,
        contentDescription = "favorite",
        modifier = modifier
            .size(size)
            .padding(padding)
            .nonEffectclickable {
                onFavorite.invoke()
            })
}

@Preview
@Composable
fun PreviewFavoriteImage() {
    FavoriteImage(
        modifier = Modifier,
        isFavorite = false,
        onFavorite = { /*TODO*/ },
        size = 50.dp,
        padding = 5.dp
    )
}