package com.sarang.torang.compose.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarang.torang.compose.feed.internal.components.FeedTopUiState
import com.sarang.torang.data.basefeed.FeedBottomEvents
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.basefeed.Sample

@Preview(showBackground = true, backgroundColor = 0xFFFDFDF6)
@Composable
fun PreviewFeed(feedItemUiState: FeedItemUiState = FeedItemUiState.Sample) {
    var isLike by remember { mutableStateOf(false) }
    var sample : FeedItemUiState by remember { mutableStateOf(feedItemUiState) }
    val feedItemClickEvents = remember { FeedItemClickEvents(feedBottomEvents = FeedBottomEvents( onLike = { isLike = !isLike })) }
    var rating : String by remember { mutableStateOf(feedItemUiState.feedTopUiState.rating.toString()) }
    Column {
        FeedItem(/* Preview */
            uiState = sample,
            events = feedItemClickEvents
        )

        Spacer(Modifier.height(10.dp)
            .fillMaxWidth()
            .background(Color.White))

        Text(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
             text = "Test",
             fontSize = 18.sp)

        TextField(value = sample.feedTopUiState.userName,
                  onValueChange = { sample = sample.copy(feedTopUiState = sample.feedTopUiState.copy(userName = it)) },
                  placeholder = { Text("name") },
                  label = { Text("Name") },
                  maxLines = 1)

        TextField(value = rating,
                  onValueChange = {
                      rating = it
                      try{
                        sample = sample.copy(feedTopUiState = sample.feedTopUiState.copy(rating = it.toFloat()))
                      } catch (e : Exception){

                      }
                                  },
                  placeholder = {},
                  label = { Text("Rating") },
                  keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                  maxLines = 1)
    }
}