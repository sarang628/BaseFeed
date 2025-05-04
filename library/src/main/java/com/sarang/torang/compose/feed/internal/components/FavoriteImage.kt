package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun FavoriteImage(
    modifier: Modifier, isFavorite: Boolean, onFavorite: () -> Unit, size: Dp,
    padding: Dp,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Icon(
        //TODO:: 별모양 outline도 검정색으로 나오는 원인 찾기
        painter = if (isFavorite) painterResource(id = R.drawable.star_filled) else painterResource(
            id = R.drawable.star
        ),
        contentDescription = "favorite",
        tint = if (isFavorite) color else Color.White,
        modifier = modifier
            .size(size)
            .padding(padding)
            .nonEffectclickable {
                onFavorite.invoke()
            },)
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

@Preview
@Composable
fun PreviewFavoriteImage1() {
    FavoriteImage(
        modifier = Modifier,
        isFavorite = true,
        onFavorite = { /*TODO*/ },
        size = 50.dp,
        padding = 5.dp
    )
}