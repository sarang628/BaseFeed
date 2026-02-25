package com.sarang.torang.compose.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.component.util.nonEffectClickable

@Composable
fun Favorite(modifier   : Modifier      = Modifier,
             isFavorite : Boolean       = false,
             onFavorite : () -> Unit    = {},
             size       : Dp            = 22.dp,
             padding    : Dp            = 0.dp,
             color      : Color         = MaterialTheme.colorScheme.primary) {
    Icon(painter = if (isFavorite) painterResource(id = R.drawable.star_filled)
                   else painterResource(id = R.drawable.star),
        contentDescription = "favorite",
        tint = if (isFavorite) color else Color.White,
        modifier = modifier
            .testTag("btnFavorite")
            .size(size)
            .padding(padding)
            .nonEffectClickable(onFavorite))
}

@Preview
@Composable
fun PreviewFavoriteImage() {
    Favorite(
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
    Favorite(
        modifier = Modifier,
        isFavorite = true,
        onFavorite = { /*TODO*/ },
        size = 50.dp,
        padding = 5.dp
    )
}