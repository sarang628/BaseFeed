package com.sarang.torang.compose.component

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.component.util.nonEffectClickable

@Composable
internal fun UserName(modifier : Modifier = Modifier, onName: () -> Unit = {}, userName : String = ""){
    Text(modifier   = modifier.widthIn(0.dp, 150.dp)
        .nonEffectClickable(onName),
        text        = userName,
        overflow    = TextOverflow.Ellipsis,
        maxLines    = 1,
        color       = Color.White,
        style       = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
}