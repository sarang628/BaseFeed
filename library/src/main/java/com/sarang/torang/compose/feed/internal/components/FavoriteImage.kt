package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun FavoriteImage(
    modifier: Modifier, isFavorite: Boolean, onFavorite: () -> Unit, size: Dp,
    padding: Dp
) {
    Icon(imageVector = if (isFavorite) Icons.Outlined.Star
    else Icons.Filled.Star,
        contentDescription = "favorite",
        modifier = modifier
            .size(size)
            .padding(padding)
            .nonEffectclickable {
                onFavorite.invoke()
            })
}