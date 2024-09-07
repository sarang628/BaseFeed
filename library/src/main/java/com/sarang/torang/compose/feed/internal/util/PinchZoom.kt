package com.sarang.torang.compose.feed.internal.util

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput

@Stable
@Composable
fun Modifier.pinchZoom(onPinchZoom: (Boolean) -> Unit): Modifier {
    val scale = remember { mutableFloatStateOf(1f) }
    val offsetX = remember { mutableFloatStateOf(1f) }
    val offsetY = remember { mutableFloatStateOf(1f) }
    val plantImageZIndex = remember { mutableFloatStateOf(1f) }
    val maxScale = remember { mutableFloatStateOf(1f) }
    val minScale = remember { mutableFloatStateOf(3f) }
    return this
        .pointerInput(Unit) {
            awaitEachGesture {
                awaitFirstDown()
                do {
                    val event = awaitPointerEvent()
                    scale.value *= event.calculateZoom()
                    if (scale.value > 1) {
                        onPinchZoom.invoke(true)
                        plantImageZIndex.value = 5f
                        val offset = event.calculatePan()
                        offsetX.value += offset.x
                        offsetY.value += offset.y
                    } else {
                        onPinchZoom.invoke(false)
                    }
                } while (event.changes.any { it.pressed })
                if (currentEvent.type == PointerEventType.Release) {
                    scale.value = 1f
                    offsetX.value = 1f
                    offsetY.value = 1f
                    plantImageZIndex.value = 1f
                }
            }
        }
        .graphicsLayer {
            scaleX = maxOf(maxScale.value, minOf(minScale.value, scale.value))
            scaleY = maxOf(maxScale.value, minOf(minScale.value, scale.value))
            translationX = offsetX.value
            translationY = offsetY.value
        }
}