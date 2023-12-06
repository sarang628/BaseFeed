package com.sryang.torang.compose.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.torang.data.basefeed.Comment

@Composable
fun FeedComments(
    comments: List<Comment>? = null,
    contents: String,
    likeAmount: Int? = 0,
    commentAmount: Int? = 0,
) {
    Column(
        Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
    ) {
        if (contents.isNotEmpty())
            Text(text = contents)

        if (likeAmount != null) {
            if (likeAmount > 0)
                Text(text = "좋아요 $likeAmount 개", color = Color.DarkGray)
        }

        comments?.forEach {
            Comment(it)
        }

        if (commentAmount != null) {
            if (commentAmount > 0)
                Text(text = "댓글 $commentAmount 개 모두보기", color = Color.DarkGray)
        }
    }

}

@Composable
fun Comment(comment: Comment) {
    Row {
        Text(text = comment.author, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(start = 3.dp))
        Text(text = comment.comment)
    }
}

@Preview
@Composable
fun PreviewFeedComments() {
    FeedComments(
        contents = "contents",
        likeAmount = 10,
        commentAmount = 10,
    )
}