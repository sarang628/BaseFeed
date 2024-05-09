package com.sarang.torang.di.image

import TorangAsyncImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun provideTorangAsyncImage(): @Composable (Modifier, String, Dp?, Dp?) -> Unit =
    { modifier, model, progressSize, errorIconSize ->
        TorangAsyncImage(
            modifier = modifier,
            model = model,
            progressSize = progressSize ?: 50.dp,
            errorIconSize = errorIconSize ?: 50.dp
        )
    }
