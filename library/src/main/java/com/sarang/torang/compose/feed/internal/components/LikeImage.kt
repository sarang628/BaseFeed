package com.sarang.torang.compose.feed.internal.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import kotlinx.coroutines.delay

@Composable
fun UnLikeImage(
    modifier: Modifier = Modifier,
    onLike: () -> Unit,
    size: Dp,
    padding: Dp,
) {
    Icon(
        imageVector = Icons.Default.FavoriteBorder,
        contentDescription = "like",
        modifier = modifier
            .size(size)
            .padding(padding)
            .nonEffectclickable {
                onLike.invoke()
            }
    )
}

@Composable
fun LikeImage(
    modifier: Modifier = Modifier,
    onLike: () -> Unit,
    size: Dp,
    padding: Dp,
) {
    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "like",
        tint = Color.Red,
        modifier = modifier
            .size(size)
            .padding(padding)
            .nonEffectclickable {
                onLike.invoke()
            }
    )
}

@Composable
fun AnimationLikeImage(
    modifier: Modifier = Modifier,
    onLike: () -> Unit,
    size: Dp,
    padding: Dp,
    onFinishAnimation: (() -> Unit)? = null
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = "") {
        delay(0)
        visible = true
        delay(200)
        onFinishAnimation?.invoke()
    }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "like",
            tint = Color.Red,
            modifier = modifier
                .size(size)
                .padding(padding)
                .nonEffectclickable {
                    onLike.invoke()
                }
        )
    }
}

@Preview
@Composable
fun PreviewLikeImage() {
    LikeImage(
        modifier = Modifier,
        onLike = { /*TODO*/ },
        size = 50.dp,
        padding = 5.dp
    )
}

@Preview
@Composable
fun PreviewUnLikeImage() {
    UnLikeImage(
        modifier = Modifier,
        onLike = { /*TODO*/ },
        size = 50.dp,
        padding = 5.dp
    )
}

@Preview
@Composable
fun PreviewAnimationLikeImage() {
    AnimationLikeImage(/*preview*/
        modifier = Modifier,
        onLike = { /*TODO*/ },
        size = 50.dp,
        padding = 5.dp
    )
}