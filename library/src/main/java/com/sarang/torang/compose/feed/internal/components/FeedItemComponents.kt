package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable

@Composable
fun Menu(modifier : Modifier = Modifier, onMenu : ()->Unit){
    IconButton(modifier = modifier.testTag("btnMenu"),
        onClick = onMenu) {
        Icon(imageVector = Icons.Default.MoreVert,
            tint = Color.White,
            contentDescription = "menu",
            modifier = Modifier.background(Color.Transparent))
    }
}

@Composable
fun RestaurantName(modifier : Modifier = Modifier, onRestaurant : ()->Unit, restaurantNeme : String = ""){
    Text(modifier = modifier.testTag("txtRestaurantName")
        .widthIn(0.dp, 250.dp)
        .nonEffectclickable(onRestaurant),
        text = restaurantNeme,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        color = Color.White,
        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
}

@Composable
fun UserName(modifier : Modifier = Modifier, onName: () -> Unit = {}, userName : String = ""){
    Text(modifier   = modifier.widthIn(0.dp, 150.dp)
        .nonEffectclickable(onName),
        text       = userName,
        overflow   = TextOverflow.Ellipsis,
        maxLines   = 1,
        color      = Color.White,
        style      = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
}

@Composable
fun CommentCount(modifier : Modifier = Modifier, count : Int, onComment : ()->Unit = {}){
    Text(modifier = modifier.clickable { onComment.invoke() },
        text = stringResource(id = R.string.comments, count),
        color = Color.Gray,
        fontWeight = W500,
        fontSize = 14.sp)
}

@Composable
fun Date(modifier : Modifier = Modifier, date : String = ""){
    Text(modifier = modifier.testTag("txtDate"),
        text = date,
        color = Color.Gray,
        fontWeight = W500,
        fontSize = 12.sp)
}

@Composable
fun LikeCount(modifier : Modifier = Modifier, onLikes : ()->Unit = {}, count : Int = 0){
    Text(modifier = modifier.testTag("txtLikes").clickable { onLikes.invoke() },
        text = "${count}",
        color = if (isSystemInDarkTheme()) Color.White else Color.White,
        fontWeight = FontWeight.Bold)
}

@Composable
fun Contents(modifier : Modifier = Modifier, userName : String = "", contents : String, onContents : ()->Unit = {}){
    LocalExpandableTextType.current.invoke(modifier.testTag("txtContents"), userName, contents, onContents)
}

@Composable
fun ProfileImage(modifier : Modifier = Modifier, onProfile: () -> Unit = {}, url : String = ""){
    LocalFeedImageLoader.current.invoke(FeedImageLoaderData(
        modifier = modifier.layoutId("imgProfile")
            .testTag("imgProfile")
            .size(32.dp)
            .nonEffectclickable(onProfile)
            .border(width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp)),
        url = url,
        progressSize = 20.dp,
        errorIconSize = 20.dp,
        contentScale = ContentScale.Crop,
        50.dp))
}
