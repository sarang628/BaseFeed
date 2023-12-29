package com.sryang.torang.compose.feed.internal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sryang.torang.data.basefeed.Comment

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comments: List<Comment>? = null,
) {
    Column(modifier = modifier) {
        comments?.forEach {
            Row(modifier = modifier) {
                Text(text = it.author, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(start = 3.dp))
                Text(text = it.comment)
            }
        }
    }
}