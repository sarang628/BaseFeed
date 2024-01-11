package com.sryang.torang.compose.feed.internal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sryang.torang.R
import com.sryang.torang.compose.feed.internal.util.clickable1

@Composable
fun FavoriteImage(modifier: Modifier, isFavorite: Boolean, onFavorite: () -> Unit) {
    Image(painter = if (isFavorite) painterResource(id = R.drawable.selected_star)
    else painterResource(id = R.drawable.star),
        contentDescription = "",
        modifier = modifier
            .size(25.dp)
            .clickable1 {
                onFavorite.invoke()
            })
}