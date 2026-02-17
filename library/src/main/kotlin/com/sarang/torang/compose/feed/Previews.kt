package com.sarang.torang.compose.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.components.FeedTopUiState
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.basefeed.Sample

@Preview(showBackground = true, backgroundColor = 0xFFFDFDF6)
@Composable
fun PreviewFeed() {
    var isLike by remember { mutableStateOf(false) }
    var sample : FeedItemUiState by remember { mutableStateOf(FeedItemUiState.Sample) }
    val feedItemClickEvents = remember { FeedItemClickEvents(onLike = { isLike = !isLike }) }
    Column {
        FeedItem(/* Preview */
            uiState = sample,
            feedItemClickEvents = feedItemClickEvents
        )

        Spacer(Modifier.height(10.dp)
            .fillMaxWidth()
            .background(Color.White))

        TextField(value = sample.feedTopUiState.userName,
            onValueChange = { sample = sample.copy(feedTopUiState = FeedTopUiState(userName = it)) },
            placeholder = { Text("name") },
            label = { Text("Name") },
            maxLines = 1)
    }
}