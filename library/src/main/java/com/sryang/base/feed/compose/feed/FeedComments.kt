package com.sryang.base.feed.compose.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FeedComments(
    contents: String? = "",
    likeAmount: Int? = 0,
    author: String? = "",
    comment: String? = "",
    commentAmount: Int? = 0,
    author1: String? = "",
    comment1: String? = "",
    author2: String? = "",
    comment2: String? = ""
) {
    Column(
        Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()) {
        if (!contents.isNullOrEmpty())
            Text(text = contents)

        if (likeAmount != null) {
            if (likeAmount > 0)
                Text(text = "좋아요 $likeAmount 개", color = Color.DarkGray)
        }

        if (!author.isNullOrEmpty())
            Row {
                Text(text = author, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(start = 3.dp))
                Text(text = comment ?: "")
            }

        if (commentAmount != null) {
            if (commentAmount > 0)
                Text(text = "댓글 $commentAmount 개 모두보기", color = Color.DarkGray)
        }

        if (!author1.isNullOrEmpty())
            Row {
                Text(text = author1, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(start = 3.dp))
                Text(text = comment1 ?: "")
            }

        if (!author2.isNullOrEmpty())
            Row {
                Text(text = author2, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(start = 3.dp))
                Text(text = comment2 ?: "")
            }
    }

}

@Preview
@Composable
fun PreviewFeedComments() {
    FeedComments(
        contents = "contents",
        likeAmount = 10,
        author1 = "author1",
        author2 = "author2",
        author = "author",
        commentAmount = 10,
        comment = "comment",
        comment1 = "comment1",
        comment2 = "comment2",
    )
}