package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun FavoriteImage(modifier: Modifier, isFavorite: Boolean, onFavorite: () -> Unit, size : Dp,
                  padding : Dp
) {
    Image(painter = if (isFavorite) painterResource(id = R.drawable.selected_star)
    else painterResource(id = R.drawable.star),
        contentDescription = "favorite",
        modifier = modifier
            .size(size)
            .padding(padding)
            .nonEffectclickable {
                onFavorite.invoke()
            })
}