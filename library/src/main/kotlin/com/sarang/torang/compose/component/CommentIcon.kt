package com.sarang.torang.compose.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.component.util.nonEffectClickable

@Composable
fun CommentIcon(modifier    : Modifier      = Modifier,
                onComment   : () -> Unit    = {},
                size        : Dp            = 25.dp,
                padding     : Dp            = 0.dp) {
    Icon(painter            = painterResource(id = R.drawable.comment),
         tint               = Color.White,
         contentDescription = "comment",
         modifier           = modifier.size(size)
                                      .padding(padding)
                                      .nonEffectClickable { onComment.invoke() })
}

@Preview
@Composable
fun PreviewCommentIcon() {
    CommentIcon(modifier    = Modifier,
                onComment   = { /*TODO*/ },
                size        = 50.dp,
                padding     = 0.dp)
}
