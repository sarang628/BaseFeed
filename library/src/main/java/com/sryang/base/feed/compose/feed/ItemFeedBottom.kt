package com.sryang.base.feed.compose.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.base.feed.uistate.FeedBottomUIState
import com.sryang.base.feed.uistate.testFeedBottomUiState

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