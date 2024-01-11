package com.sryang.torang.compose.feed.internal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sryang.torang.R
import com.sryang.torang.compose.feed.internal.util.clickable1

@Composable
fun ShareImage(modifier: Modifier, onShare: () -> Unit) {
    Image(painter = painterResource(id = R.drawable.message),
        contentDescription = "",
        modifier = modifier
            .layoutId("share")
            .size(25.dp)
            .clickable1 {
                onShare.invoke()
            })
}