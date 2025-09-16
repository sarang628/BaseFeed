package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.data.basefeed.Comment

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comments: List<Comment>? = null,
) {
    Column(modifier = modifier.layoutId("comments").fillMaxWidth()) {
        comments?.forEach {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = it.author, fontWeight = FontWeight.Bold, color = Color.Gray)
                Spacer(modifier = Modifier.padding(start = 3.dp))
                Text(text = it.comment, color = Color.Gray)
            }
        }
    }
}

@Preview
@Composable
fun PreviewComment() {
    Comment(
        comments = listOf(
            Comment("1", "aa"),
            Comment("2", "bb"),
            Comment("3", "cc"),
            Comment("4", "dd"),
        )
    )
}