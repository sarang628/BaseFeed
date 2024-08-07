package com.sarang.torang.compose.feed.internal.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import kotlinx.coroutines.delay

@Composable
fun LikeImage(modifier: Modifier, isLike: Boolean, onLike: () -> Unit) {

    var isAnimationLike by remember { mutableStateOf(false) }
    var isLike1 by remember { mutableStateOf(isLike) }

    Box(
        modifier = modifier
    ) {
        if (isLike1) { //서버에서 받았을 경우 + 좋아요 애니메이션 후
            LikeImage(
                modifier = Modifier.testTag("btnLike"),
                onLike = {
                    onLike.invoke()
                    isLike1 = false
                    isAnimationLike = false
                },
                size = 42.dp,
                padding = 8.5.dp
            )
        } else if (isAnimationLike) {
            AnimationLikeImage(
                onLike = {},
                size = 42.dp,
                padding = 8.5.dp,
                onFinishAnimation = {
                    isLike1 = true
                }
            )
        } else {
            UnLikeImage(
                modifier = Modifier.testTag("btnUnLike"),
                onLike = {
                    isAnimationLike = true
                    onLike.invoke()
                },
                size = 42.dp,
                padding = 8.5.dp
            )
        }
    }
}

@Preview
@Composable
fun PreviewLikeImage1() {
    Box(modifier = Modifier.size(50.dp))
    {
        LikeImage(
            modifier = Modifier.size(50.dp),
            isLike = false,
            onLike = { /*TODO*/ }
        )
    }
}

@Composable
private fun UnLikeImage(
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
private fun LikeImage(
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
private fun AnimationLikeImage(
    modifier: Modifier = Modifier,
    onLike: () -> Unit,
    size: Dp,
    padding: Dp,
    onFinishAnimation: (() -> Unit)? = null,
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = "") {
        delay(0)
        visible = true
        delay(200)
        onFinishAnimation?.invoke()
    }
    AnimatedVisibility(
        visible = visible,
        enter =
        scaleIn(
            initialScale = 0.1f,
            animationSpec = tween(durationMillis = 300)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(durationMillis = 300)
        ),
        exit = scaleOut(
            targetScale = 1.5f,
            animationSpec = tween(durationMillis = 300)
        ) + fadeOut(
            animationSpec = tween(durationMillis = 300)
        )
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