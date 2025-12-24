package com.sarang.torang.compose.feed.internal.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun Like(modifier   : Modifier      = Modifier,
         isLike     : Boolean       = false,
         onLike     : () -> Unit    = {},
         animation  : Boolean       = true) {

    var isAnimationLike by remember { mutableStateOf(false) }
    var isLike by remember { mutableStateOf(isLike) }

    Box(modifier = modifier.layoutId("imgLike")) {
        if (isLike) { //서버에서 받았을 경우 + 좋아요 애니메이션 후
            LikeIcon(modifier   = Modifier.testTag("btnLike"),
                     onLike     = { onLike.invoke()
                                    isLike = false
                                    isAnimationLike = false },
                     size       = 26.dp,
                     padding    = 0.dp)
        } else if (isAnimationLike) {
            AnimationLikeImage(size                 = 26.dp,
                               padding              = 0.dp,
                               onFinishAnimation    = { isLike = true })
        } else {
            UnLikeIcon(modifier = Modifier.testTag("btnUnLike"),
                       size     = 26.dp,
                       padding  = 0.dp,
                       onLike   = { onLike.invoke()
                                    if (animation)
                                        isAnimationLike = true
                                  })
        }
    }
}

@Preview(widthDp = 300, backgroundColor = 0xFF000000, showBackground = true)
@Composable
private fun AnimationLikeImage(modifier         : Modifier      = Modifier,
                               onLike           : () -> Unit    = {},
                               size             : Dp            = 50.dp,
                               padding          : Dp            = 0.dp,
                               onFinishAnimation: () -> Unit    = {} ) {
    val scale = remember { Animatable(0.5f) }
    LaunchedEffect(Unit) {
        scale.animateTo(targetValue = 1.3f, animationSpec = tween(durationMillis = 150))
        scale.animateTo(targetValue = 1.0f, animationSpec = tween(durationMillis = 150))
        onFinishAnimation.invoke() }

    Icon(modifier           = modifier.size(size)
                                      .padding(padding)
                                      .graphicsLayer(scaleX = scale.value,
                                                     scaleY = scale.value)
                                      .nonEffectclickable { onLike.invoke() },
        imageVector         = Icons.Default.Favorite,
        contentDescription  = "like",
        tint                = Color.Red)
}

@Preview(widthDp = 300, backgroundColor = 0xFF000000, showBackground = true)
@Composable
fun PreviewLike(){
    var isLike : Boolean  by remember { mutableStateOf(false) }
    Box{
        Like(modifier   = Modifier.align(Alignment.Center),
             isLike     = isLike,
             onLike     = {isLike = !isLike})
    }
}