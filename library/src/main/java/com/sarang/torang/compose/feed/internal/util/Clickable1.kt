package com.sarang.torang.compose.feed.internal.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Modifier.nonEffectclickable(onClick: () -> Unit): Modifier {
    val interactionSource = remember { MutableInteractionSource() } // 클릭 시 리플 애니메이션을 없애기 위한 변수
    return this.clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
    )
}