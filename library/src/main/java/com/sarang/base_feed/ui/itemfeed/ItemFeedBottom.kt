package com.sarang.base_feed.ui.itemfeed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.base_feed.uistate.FeedBottomUIState
import com.sarang.base_feed.uistate.testFeedBottomUiState

@Composable
fun FeedBottom(
    uiState: FeedBottomUIState,     // 하단 UI 상태 값
    onLike: () -> Unit,        // 좋아요 클릭
    onComment: () -> Unit,     // 코멘트 클릭
    onShare: () -> Unit,       // 공유 클릭
    onFavorite: () -> Unit,    // 즐겨찾기 클릭
) {
    Column(Modifier.padding()) {
        FeedReaction(
            onFavorite = onFavorite,
            onComment = onComment,
            onLike = onLike,
            onShare = onShare,
            isLike = uiState.isLike,
            isFavorite = uiState.isFavorite
        )
        Spacer(modifier = Modifier.height(8.dp))
        FeedComments(
            contents = uiState.contents,
            likeAmount = uiState.likeAmount,
            author = uiState.author,
            comment = uiState.comment,
            commentAmount = uiState.commentAmount,
            author1 = uiState.author1,
            author2 = uiState.author2,
            comment1 = uiState.comment1,
            comment2 = uiState.comment2
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

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
    Column(Modifier.padding(start = 8.dp)) {
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
fun PreViewItemFeedBottom() {
    Column {
        FeedBottom(
            uiState = testFeedBottomUiState(),
            onLike = { /*TODO*/ },
            onComment = { /*TODO*/ },
            onShare = { /*TODO*/ },
            onFavorite = {})
    }
}