package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.util.nonEffectClickable

@Preview(widthDp = 145, backgroundColor = 0xFF000000, showBackground = true)
@Composable
internal fun LikeIcon(modifier  : Modifier      = Modifier,
                      onLike    : () -> Unit    = {},
                      size      : Dp            = 50.dp,
                      padding   : Dp            = 0.dp) {
    Icon(modifier           = modifier.size(size)
                                      .padding(padding)
                                      .nonEffectClickable { onLike.invoke() },
        imageVector         = Icons.Default.Favorite,
        contentDescription  = "like",
        tint                = Color.Red)
}

@Preview(widthDp = 145, backgroundColor = 0xFF000000, showBackground = true)
@Composable
internal fun UnLikeIcon(modifier    : Modifier      = Modifier,
                        onLike      : () -> Unit    = {},
                        size        : Dp            = 50.dp,
                        padding     : Dp            = 0.dp ) {
    Icon(modifier           = modifier.size(size)
                                      .padding(padding)
                                      .nonEffectClickable { onLike.invoke() },
         imageVector        = Icons.Default.FavoriteBorder,
         contentDescription = "like",
         tint               = Color.White)
}