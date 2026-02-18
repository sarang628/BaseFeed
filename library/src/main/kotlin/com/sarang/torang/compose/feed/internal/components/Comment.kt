package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarang.torang.R
import com.sarang.torang.data.basefeed.Comment
import kotlin.collections.forEach

@Composable
internal fun Comment(modifier       : Modifier      = Modifier,
                     commentCount   : Int           = 0,
                     comments       : List<Comment> = emptyList(),
                     onComment      : () -> Unit    = {}){
    Column(modifier = modifier) {
        Comment    (comments = comments)

        if(commentCount > 0)
            CommentCount    (count      = commentCount,
                             onComment  = onComment)
    }
}


@Composable
internal fun Comment(modifier : Modifier          = Modifier,
                     comments : List<Comment>    = emptyList()) {
    Column(modifier = modifier.layoutId("comments")
        .fillMaxWidth()) {
        comments.forEach {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text       = it.author,
                    fontWeight = FontWeight.Bold,
                    color      = Color.Gray)
                Spacer(modifier = Modifier.padding(start = 3.dp))
                Text(text   = it.comment,
                    color  = Color.Gray)
            }
        }
    }
}

@Composable
fun CommentCount(modifier   : Modifier  = Modifier,
                 count      : Int       = 0,
                 onComment  : ()->Unit  = {}){
    Text(modifier   = modifier.clickable { onComment() },
        text       = stringResource(id = R.string.comments, count),
        color      = Color.Gray,
        fontWeight = W500,
        fontSize   = 14.sp)
}

@Preview
@Composable
fun PreviewComment() {
    Comment(
        comments = listOf(
            Comment("Tom", "Wow!"),
            Comment("Jhon", "Nice!"),
            Comment("Amy", "Delicious!"),
            Comment("Jane", "Hello!"),
        ),
        commentCount = 120
    )
}

@Preview
@Composable
fun PreviewCommentCount(){
    CommentCount(count = 100)
}