package com.sarang.torang.compose.feed.internal.components.test

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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview(widthDp = 300, heightDp = 300)
@Composable
fun PreviewAnimatedVisibilityExample() {
    var visible by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { visible = !visible }) { Text("Toggle Visibility") }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(
            visible = visible,
            enter = scaleIn(initialScale = 0.5f, animationSpec = tween(durationMillis = 300)) +
                    scaleIn(initialScale = 1.5f, animationSpec = tween(durationMillis = 300, delayMillis = 300)) +
                    fadeIn(initialAlpha = 0.3f, animationSpec = tween(durationMillis = 600)
                    ),
            exit = scaleOut(targetScale = 0.3f, animationSpec = tween(durationMillis = 300)) +
                    fadeOut(animationSpec = tween(durationMillis = 300))
        ) { Box(modifier = Modifier.size(100.dp).background(Color.Red)) }
    }
}

@Preview(widthDp = 300, heightDp = 300)
@Composable
fun PreviewBoxScaleAnimation() {
    val scale = remember { Animatable(0.5f) }

    LaunchedEffect(Unit) {
        delay(100)
        scale.animateTo(targetValue = 1.5f, animationSpec = tween(durationMillis = 300))
        delay(10)  // Optional delay for smoother transition
        scale.animateTo(targetValue = 0.5f, animationSpec = tween(durationMillis = 300)) }

    Box(modifier = Modifier.size(100.dp).graphicsLayer(scaleX = scale.value, scaleY = scale.value).background(Color.Red))
}