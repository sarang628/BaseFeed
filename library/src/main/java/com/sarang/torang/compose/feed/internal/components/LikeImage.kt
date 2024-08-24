package com.sarang.torang.compose.feed.internal.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import kotlinx.coroutines.delay

@Composable
fun LikeImage(modifier: Modifier, isLike: Boolean, onLike: () -> Unit, animation: Boolean = true) {

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
                    onLike.invoke()
                    if (animation) {
                        isAnimationLike = true
                    }
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
            modifier = Modifier
                .size(50.dp)
                .padding(5.dp),
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
    val scale = remember { Animatable(0.5f) }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1.3f,
            animationSpec = tween(durationMillis = 150)
        )
        scale.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(durationMillis = 150)
        )
        onFinishAnimation?.invoke()
    }

    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "like",
        tint = Color.Red,
        modifier = modifier
            .size(size)
            .padding(padding)
            .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
            .nonEffectclickable {
                onLike.invoke()
            }
    )
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


@Preview
@Composable
fun AnimatedVisibilityExample() {
    var visible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { visible = !visible }) {
            Text("Toggle Visibility")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(
            visible = visible,
            enter = scaleIn(
                initialScale = 0.5f,
                animationSpec = tween(durationMillis = 300)
            ) + scaleIn(
                initialScale = 1.5f,
                animationSpec = tween(durationMillis = 300, delayMillis = 300)
            ) + fadeIn(
                initialAlpha = 0.3f,
                animationSpec = tween(durationMillis = 600)
            ),
            exit = scaleOut(
                targetScale = 0.3f,
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(
                animationSpec = tween(durationMillis = 300)
            )
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Preview
@Composable
fun BoxWithScaleAnimation() {
    val scale = remember { Animatable(0.5f) }

    LaunchedEffect(Unit) {
        delay(100)
        scale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(durationMillis = 300)
        )
        delay(10)  // Optional delay for smoother transition
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 300)
        )
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
            .background(Color.Red)
    )
}