package com.sarang.torang.compose.feed.internal.components.type

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

typealias ExpandableTextType = @Composable (Modifier, String, String, () -> Unit) -> Unit

val LocalExpandableTextType = compositionLocalOf<ExpandableTextType> {
    @Composable { modifier, name, contents, _ ->
        Text(modifier = modifier, text = "$name $contents")
    }
}