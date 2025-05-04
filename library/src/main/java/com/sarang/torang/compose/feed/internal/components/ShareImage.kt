package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun ShareImage(
    modifier: Modifier = Modifier, onShare: () -> Unit, size: Dp,
    padding: Dp
) {
    Icon(painter = painterResource(id = R.drawable.share),
        contentDescription = "share",
        modifier = modifier
            .layoutId("share")
            .size(size)
            .padding(padding)
            .nonEffectclickable {
                onShare.invoke()
            },
        tint = Color.White)
}

@Preview
@Composable
fun PreviewShareImage() {
    ShareImage(onShare = { /*TODO*/ }, size = 50.dp, padding = 0.dp)
}