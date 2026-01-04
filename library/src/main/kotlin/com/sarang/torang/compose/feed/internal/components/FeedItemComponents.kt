package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.components.type.ExpandableTextData
import com.sarang.torang.compose.feed.internal.components.type.FeedImageLoaderData
import com.sarang.torang.compose.feed.internal.components.type.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.type.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.util.nonEffectClickable
import com.sarang.torang.data.basefeed.Comment
import com.sarang.torang.data.basefeed.formatedDate
import kotlin.collections.forEach

@Preview
@Composable
fun Menu(modifier   : Modifier = Modifier,
         onMenu     : ()->Unit = {}){
    IconButton(modifier = modifier,
               onClick  = onMenu) {
        Icon(imageVector        = Icons.Default.MoreVert,
             tint               = Color.White,
             contentDescription = "menu",
             modifier           = Modifier.background(Color.Transparent))
    }
}

@Composable
fun RestaurantName(modifier         : Modifier  = Modifier,
                   onRestaurant     : ()->Unit  = {},
                   restaurantName   : String    = ""){
    Text(modifier   = modifier.widthIn(0.dp, 250.dp)
                              .nonEffectClickable(onRestaurant),
         text       = restaurantName,
         overflow   = TextOverflow.Ellipsis,
         maxLines   = 1,
         color      = Color.White,
         style      = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
    )
}

@Preview
@Composable
fun PreviewRestaurantName(){
    val restaurantName = "restaurantName restaurantName restaurantName restaurantName"
    Column {
        RestaurantName(restaurantName = restaurantName)
        Text(text = restaurantName,
             color = Color.White,
             maxLines = 1,
             //style      = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )
    }

}

@Composable
fun UserName(modifier : Modifier = Modifier, onName: () -> Unit = {}, userName : String = ""){
    Text(modifier   = modifier.widthIn(0.dp, 150.dp)
                              .nonEffectClickable(onName),
        text        = userName,
        overflow    = TextOverflow.Ellipsis,
        maxLines    = 1,
        color       = Color.White,
        style       = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
}

@Composable
fun CommentCount(modifier : Modifier = Modifier, count : Int, onComment : ()->Unit = {}){
    Text(modifier = modifier.clickable { onComment.invoke() },
        text = stringResource(id = R.string.comments, count),
        color = Color.Gray,
        fontWeight = W500,
        fontSize = 14.sp)
}

@Preview
@Composable
fun PreviewCommentCount(){
    CommentCount(count = 100)
}

@Composable
fun Date(modifier : Modifier = Modifier, date : String = ""){
    Text(modifier = modifier.testTag("txtDate"),
        text = date.formatedDate(),
        color = Color.Gray,
        fontWeight = W500,
        fontSize = 12.sp)
}

@Preview
@Composable
fun PreviewDate(){
    Date(date = "2025-12-30 12:00:00")
}

@Composable
fun LikeCount(modifier : Modifier = Modifier, onLikes : ()->Unit = {}, count : Int = 0){
    Text(modifier = modifier.testTag("txtLikes").clickable { onLikes.invoke() },
        text = "${count}",
        color = if (isSystemInDarkTheme()) Color.White else Color.White,
        fontWeight = FontWeight.Bold)
}

@Composable
fun Contents(modifier   : Modifier  = Modifier,
             userName   : String    = "",
             contents   : String    = "",
             onContents : ()->Unit  = {}){
    LocalExpandableTextType.current.invoke(
        ExpandableTextData(modifier = modifier.testTag("txtContents"),
                                nickName = userName,
                                contents = contents,
                                onNickName = onContents)
    )
}

@Preview
@Composable
fun PreviewContent(){
    Surface(color = MaterialTheme.colorScheme.background) {
        Contents(userName = "userName", contents = "contents")
    }
}

@Composable
fun ProfileImage(modifier : Modifier = Modifier, onProfile: () -> Unit = {}, url : String = ""){
    LocalFeedImageLoader.current.invoke(
        FeedImageLoaderData(
            modifier        = modifier.size(32.dp)
                                      .nonEffectClickable(onProfile)
                                      .border(width = 0.5.dp,
                                              color = Color.LightGray,
                                              shape = RoundedCornerShape(20.dp))
                                      .clip(RoundedCornerShape(20.dp)),
            url             = url,
            progressSize    = 20.dp,
            errorIconSize   = 20.dp,
            contentScale    = ContentScale.Crop,
            height          = 50.dp
        )
    )
}


@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comments: List<Comment>? = null,
) {
    Column(modifier = modifier.layoutId("comments").fillMaxWidth()) {
        comments?.forEach {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = it.author, fontWeight = FontWeight.Bold, color = Color.Gray)
                Spacer(modifier = Modifier.padding(start = 3.dp))
                Text(text = it.comment, color = Color.Gray)
            }
        }
    }
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
        )
    )
}