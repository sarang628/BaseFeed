package com.sryang.torang.compose.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.torang.data.basefeed.Comment

@Composable
internal fun FeedBottom(
    comments: List<Comment>? = null,
    contents: String,
    likeAmount: Int? = 0,
    commentAmount: Int? = 0,
    isLike: Boolean,
    isFavorite: Boolean,
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
            isLike = isLike,
            isFavorite = isFavorite
        )
        Spacer(modifier = Modifier.height(8.dp))
        FeedComments(
            contents = contents,
            likeAmount = likeAmount,
            commentAmount = commentAmount,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}


@Preview
@Composable
fun PreViewItemFeedBottom() {
    Column {
        FeedBottom(
            comments = ArrayList(),
            commentAmount = 0,
            likeAmount = 0,
            isFavorite = false,
            isLike = false,
            contents = "",
            onLike = { /*TODO*/ },
            onComment = { /*TODO*/ },
            onShare = { /*TODO*/ },
            onFavorite = {})
    }
}