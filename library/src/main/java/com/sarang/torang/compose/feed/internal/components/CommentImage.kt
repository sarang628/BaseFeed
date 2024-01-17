package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.clickable1

@Composable
fun CommentImage(modifier: Modifier, onComment: () -> Unit) {
    Image(painter = painterResource(id = R.drawable.chat),
        contentDescription = "",
        modifier = modifier
            .size(25.dp)
            .layoutId("comment")
            .clickable1 {
                onComment.invoke()
            })
}