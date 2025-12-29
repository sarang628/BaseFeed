package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.nonEffectClickable

@Composable
fun Share(modifier  : Modifier      = Modifier,
          onShare   : () -> Unit    = {},
          size      : Dp            = 25.dp,
          padding   : Dp            = 0.dp) {
    Icon(painter = painterResource(id = R.drawable.share),
        contentDescription = "share",
        modifier = modifier.layoutId("imgShare").testTag("btnShare")
            .size(size)
            .padding(padding)
            .nonEffectClickable {
                onShare.invoke()
            },
        tint = Color.White)
}

@Preview
@Composable
fun PreviewShareImage() {
    Share(onShare = { /*TODO*/ }, size = 50.dp, padding = 0.dp)
}