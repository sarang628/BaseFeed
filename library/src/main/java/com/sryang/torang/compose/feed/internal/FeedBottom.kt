package com.sryang.torang.compose.feed.internal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.sryang.torang.R
import com.sryang.torang.data.basefeed.Comment
import com.sryang.torang.data.basefeed.testReviewData

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
    ConstraintLayout(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        constraintSet = feedCommentsConstraint()

    ) {
        Image(
            painter = if (isLike) painterResource(id = R.drawable.selected_heart)
            else painterResource(id = R.drawable.b3s),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .layoutId("heart")
                .clickable1 {
                    onLike.invoke()
                }
        )

        Image(
            painter = painterResource(id = R.drawable.chat),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .layoutId("comment")
                .clickable1 {
                    onComment.invoke()
                }
        )

        Image(
            painter = painterResource(id = R.drawable.message),
            contentDescription = "",
            modifier = Modifier
                .layoutId("share")
                .size(25.dp)
                .clickable1 {
                    onShare.invoke()
                }
        )

        Image(
            painter = if (isFavorite) painterResource(id = R.drawable.selected_star)
            else painterResource(id = R.drawable.star),
            contentDescription = "",
            modifier = Modifier
                .layoutId("favorite")
                .size(25.dp)
                .clickable1 {
                    onFavorite.invoke()
                }
        )

        if (contents.isNotEmpty()) {
            ExpandableText(modifier = Modifier.layoutId("contents"), text = contents)
        }

        if (likeAmount != null && likeAmount > 0) {
            Text(
                modifier = Modifier.layoutId("likeCount"),
                text = "좋아요 $likeAmount 개",
                color = Color.DarkGray
            )
        }

        Comment(Modifier.layoutId("comments"), comments)

        if (commentAmount != null && commentAmount > 0) {
            Text(
                modifier = Modifier.layoutId("commentCount"),
                text = "댓글 $commentAmount 개 모두보기",
                color = Color.DarkGray
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

fun feedCommentsConstraint(): ConstraintSet {
    return ConstraintSet {
        val contents = createRefFor("contents")
        val comments = createRefFor("comments")
        val likeCount = createRefFor("likeCount")
        val commentCount = createRefFor("commentCount")
        val heart = createRefFor("heart")
        val comment = createRefFor("comment")
        val share = createRefFor("share")
        val favorite = createRefFor("favorite")

        constrain(heart) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }

        constrain(comment) {
            top.linkTo(parent.top)
            start.linkTo(heart.end, margin = 8.dp)
        }

        constrain(share) {
            top.linkTo(parent.top)
            start.linkTo(comment.end, margin = 8.dp)
        }

        constrain(favorite) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }

        constrain(contents) {
            top.linkTo(heart.bottom, margin = 8.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(likeCount) {
            top.linkTo(contents.bottom)
        }
        constrain(comments) {
            top.linkTo(likeCount.bottom)
        }
        constrain(commentCount) {
            top.linkTo(comments.bottom)
        }
    }
}


@Preview
@Composable
fun PreViewItemFeedBottom() {
    FeedBottom(
        comments = ArrayList(),
        commentAmount = 10,
        likeAmount = 10,
        isFavorite = true,
        isLike = true,
        contents = testReviewData().contents,
        onLike = { /*TODO*/ },
        onComment = { /*TODO*/ },
        onShare = { /*TODO*/ },
        onFavorite = {})
}