package com.sarang.torang.compose.feed.internal.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

typealias ExpandableTextType = @Composable (Modifier, String, String, () -> Unit) -> Unit

val LocalExpandableTextType = compositionLocalOf<ExpandableTextType> {
    @Composable { _, _, _, _ -> }
}