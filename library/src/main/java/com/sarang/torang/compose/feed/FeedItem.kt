package com.sarang.torang.compose.feed

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.substring
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.sarang.torang.R
import com.sarang.torang.compose.component.AndroidViewRatingBar
import com.sarang.torang.compose.feed.internal.components.Comment
import com.sarang.torang.compose.feed.internal.components.Favorite
import com.sarang.torang.compose.feed.internal.components.FeedImageLoaderData
import com.sarang.torang.compose.feed.internal.components.ImagePagerWithIndicator
import com.sarang.torang.compose.feed.internal.components.Like
import com.sarang.torang.compose.feed.internal.components.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.components.Share
import com.sarang.torang.compose.feed.internal.components.d
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import com.sarang.torang.data.basefeed.FeedItemPageEvent
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.basefeed.Sample
import com.sarang.torang.data.basefeed.empty
import com.sarang.torang.data.basefeed.formatedDate

// @formatter:off
/**
 * Feed 항목
 * @param uiState            리뷰 데이터
 * @param progressTintColor ratingBar 색
 * @param favoriteColor     즐겨찾기 색
 * @param onPage            페이지 콜백 Int: 현재 페이지, Boolean: 첫번째 페이지 여부, Boolean: 마지막 페이지 여부
 */
@Composable
fun FeedItem(
    tag                 : String                        = "__Feed",
    showLog             : Boolean                       = false,
    uiState             : FeedItemUiState               = FeedItemUiState.empty,
    progressTintColor   : Color                         = Color(0xffe6cc00),
    favoriteColor       : Color                         = Color(0xffe6cc00),
    pageScrollAble      : Boolean                       = true,
    feedItemClickEvents : FeedItemClickEvents           = FeedItemClickEvents(tag = tag),
    onPage              : (FeedItemPageEvent) -> Unit   = { feedItemPageEvent -> Log.w(tag, "onPage callback isn't set page: ${feedItemPageEvent.page} isFirst: ${feedItemPageEvent.isFirst} isLast: ${feedItemPageEvent.isLast}") }
) {
    val interactionSource = remember { MutableInteractionSource() }
    ConstraintLayout(modifier = Modifier.fillMaxWidth(), constraintSet = feedItemConstraintSet(uiState.likeAmount > 0, uiState.contents.isNotEmpty(), uiState.commentAmount > 0, uiState.restaurantName.isNotEmpty())) {
        showLog.d(tag, "contents : ${uiState.contents.substring(IntRange(0,10))}, height : ${uiState.height}")
        ImagePagerWithIndicator (
            images          = uiState.reviewImages,
            onImage         = feedItemClickEvents.onImage,
            showIndicator   = true,
            height          = with(LocalDensity.current) { uiState.height.toDp() },
            scrollEnable    = pageScrollAble,
            onPage          = onPage,
            showLog         = showLog
        )
        Box                     (modifier = Modifier.layoutId("clickBlockBehindProfile").clickable(interactionSource = interactionSource, indication = null, onClick = {}))
        Box                     (modifier = Modifier.layoutId("clickBlockBehindBottom").clickable(interactionSource = interactionSource, indication = null, onClick = {}))
        UserName                (userName       = uiState.userName          , onName = feedItemClickEvents.onName)
        LikeCount               (count          = uiState.likeAmount        , onLikes = feedItemClickEvents.onLike)
        CommentCount            (count          = uiState.commentAmount     , onComment = feedItemClickEvents.onComment)
        Like                    (isLike         = uiState.isLike            , onLike = feedItemClickEvents.onLike, animation = uiState.isLogin)
        Favorite                (isFavorite     = uiState.isFavorite        , onFavorite = feedItemClickEvents.onFavorite, color = favoriteColor)
        ProfileImage            (url            = uiState.profilePictureUrl , onProfile = feedItemClickEvents.onProfile)
        RestaurantName          (restaurantNeme = uiState.restaurantName    , onRestaurant = feedItemClickEvents.onRestaurant)
        Contents                (userName       = uiState.userName, contents = uiState.contents, onContents = feedItemClickEvents.onProfile)
        AndroidViewRatingBar    (rating         = uiState.rating, progressTintColor = progressTintColor)
        Comment                 (comments       = uiState.comments)
        Date                    (date           = uiState.formatedDate())
        Share                   (onShare = feedItemClickEvents.onShare)
        Comment                 (onComment = feedItemClickEvents.onComment)
        Menu                    (onMenu = feedItemClickEvents.onMenu)
    }
}

@Composable
fun Contents(modifier : Modifier = Modifier, userName : String = "", contents : String, onContents : ()->Unit = {}){
    LocalExpandableTextType.current.invoke(modifier.layoutId("contents").testTag("txtContents"), userName, contents, onContents)
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

@Composable
fun UserName(modifier : Modifier = Modifier, onName: () -> Unit = {}, userName : String = ""){
    Text(modifier = modifier.layoutId("txtName").widthIn(0.dp, 150.dp).nonEffectclickable(onName),
        text = userName, overflow = TextOverflow.Ellipsis, maxLines = 1, color = Color.White, style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
}

@Composable
fun Date(modifier : Modifier = Modifier, date : String = ""){
    Text(modifier = modifier.layoutId("date").testTag("txtDate"), text = date, color = Color.Gray, fontWeight = W500, fontSize = 12.sp)
}

@Composable
fun CommentCount(modifier : Modifier = Modifier, count : Int, onComment : ()->Unit = {}){
    Text(modifier = modifier.layoutId("commentCount").clickable { onComment.invoke() }, text = stringResource(id = R.string.comments, count), color = Color.Gray, fontWeight = W500, fontSize = 14.sp)
}

@Composable
fun LikeCount(modifier : Modifier = Modifier, onLikes : ()->Unit = {}, count : Int = 0){
    Text(modifier = modifier.layoutId("likeCount").testTag("txtLikes").clickable { onLikes.invoke() }, text = stringResource(id = R.string.like, count), color = if (isSystemInDarkTheme()) Color.White else Color.White, fontWeight = FontWeight.Bold)
}

@Composable
fun Menu(modifier : Modifier = Modifier, onMenu : ()->Unit){
    IconButton(modifier = modifier.layoutId("menu").testTag("btnMenu"), onClick = onMenu) {
        Icon(imageVector = Icons.Default.MoreVert, tint = Color.White, contentDescription = "menu", modifier = Modifier.background(Color.Transparent))
    }
}

@Composable
fun RestaurantName(modifier : Modifier = Modifier, onRestaurant : ()->Unit, restaurantNeme : String = ""){
    Text(modifier = modifier.layoutId("restaurantName").testTag("txtRestaurantName").widthIn(0.dp, 250.dp).nonEffectclickable(onRestaurant),
        text = restaurantNeme, overflow = TextOverflow.Ellipsis, maxLines = 1, color = Color.White, style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
}

/**
 * @param onImage           이미지 클릭
 * @param onProfile         프로필 클릭
 * @param onLike            좋아요 클릭
 * @param onComment         코멘트 클릭
 * @param onShare           공유 클릭
 * @param onFavorite        즐겨찾기 클릭
 * @param onMenu            메뉴 클릭
 * @param onName            사용자명 클릭
 * @param onRestaurant      음식점명 클릭
 * @param onLikes           좋아요 클릭
 */
data class FeedItemClickEvents (
    val tag                 : String                    = "__FeedItemClickEvents",
    val onLike              : () -> Unit                = { Log.w(tag, "onLike callback is not set") },
    val onProfile           : () -> Unit                = { Log.w(tag, "onProfile callback is not set") },
    val onComment           : () -> Unit                = { Log.w(tag, "onComment callback is not set") },
    val onShare             : () -> Unit                = { Log.w(tag, "onShare callback is not set") },
    val onMenu              : () -> Unit                = { Log.w(tag, "onMenu callback is not set") },
    val onFavorite          : () -> Unit                = { Log.w(tag, "onFavorite callback is not set") },
    val onName              : () -> Unit                = { Log.w(tag, "onName callback is not set") },
    val onRestaurant        : () -> Unit                = { Log.w(tag, "onRestaurant callback is not set") },
    val onLikes             : () -> Unit                = { Log.w(tag, "onLikes callback is not set") },
    val onImage             : (Int) -> Unit             = { Log.w(tag, "onImage callback is not set") },
)

fun feedItemConstraintSet(visibleLike : Boolean = false, visibieContents : Boolean = false, visibleCommentCount : Boolean = false, visibleRestaurantName : Boolean = false) : ConstraintSet{
    return ConstraintSet{
        val likeCount               = createRefFor("likeCount")
        val reviewImages            = createRefFor("reviewImages")
        val contents                = createRefFor("contents")
        val comments                = createRefFor("comments")
        val commentCount            = createRefFor("commentCount")
        val date                    = createRefFor("date")
        val imgLike                 = createRefFor("imgLike")
        val imgComment              = createRefFor("imgComment")
        val imgShare                = createRefFor("imgShare")
        val indicator               = createRefFor("indicator")
        val imgFavorite             = createRefFor("imgFavorite")
        val imgProfile              = createRefFor("imgProfile")
        val txtName                 = createRefFor("txtName")
        val ratingBar               = createRefFor("ratingBar")
        val restaurantName          = createRefFor("restaurantName")
        val menu                    = createRefFor("menu")
        val clickBlockBehindProfile = createRefFor("clickBlockBehindProfile")
        val clickBlockBehindBottom  = createRefFor("clickBlockBehindBottom")
        val guideline       = createBottomBarrier(reviewImages, margin = 4.dp)

        constrain(reviewImages)     { top.linkTo(parent.top) }
        constrain(likeCount)        { bottom.linkTo(imgLike.top); start.linkTo(reviewImages.start, 8.dp); visibility = if(visibleLike) Visibility.Visible else Visibility.Gone }
        constrain(commentCount)     { start.linkTo(parent.start, 4.dp); top.linkTo(guideline); visibility = if(visibleCommentCount) Visibility.Visible else Visibility.Gone}
        constrain(contents)         { start.linkTo(parent.start, 4.dp); end.linkTo(parent.end, 4.dp); top.linkTo(commentCount.bottom); visibility = if(visibieContents) Visibility.Visible else Visibility.Gone; width = Dimension.fillToConstraints }
        constrain(comments)         { top.linkTo(contents.bottom); visibility = if(visibleCommentCount) Visibility.Visible else Visibility.Gone }
        constrain(date)             { start.linkTo(parent.start, 4.dp); top.linkTo(comments.bottom, 8.dp); }

        constrain(imgLike)          { start.linkTo(reviewImages.start); bottom.linkTo(reviewImages.bottom); }
        constrain(imgComment)       { start.linkTo(imgLike.end); bottom.linkTo(reviewImages.bottom); }
        constrain(imgShare)         { start.linkTo(imgComment.end); bottom.linkTo(reviewImages.bottom); }
        constrain(indicator)        { start.linkTo(reviewImages.start); end.linkTo(reviewImages.end); top.linkTo(imgLike.top); bottom.linkTo(imgLike.bottom); }
        constrain(imgFavorite)      { end.linkTo(reviewImages.end); bottom.linkTo(reviewImages.bottom) }

        constrain(imgProfile)       { top.linkTo(reviewImages.top, 4.dp); start.linkTo(reviewImages.start, 4.dp) }
        constrain(txtName)          { start.linkTo(imgProfile.end, 4.dp); top.linkTo(imgProfile.top); bottom.linkTo(restaurantName.top) }
        constrain(ratingBar)        { start.linkTo(txtName.end, 4.dp); top.linkTo(txtName.top); bottom.linkTo(txtName.bottom) }
        constrain(restaurantName)   { top.linkTo(txtName.bottom); bottom.linkTo(imgProfile.bottom); start.linkTo(imgProfile.end, 4.dp); }
        constrain(menu)             { end.linkTo(parent.end) }

        constrain(clickBlockBehindProfile)  { start.linkTo(parent.start); end.linkTo(parent.end); top.linkTo(parent.top); bottom.linkTo(menu.bottom); width = Dimension.fillToConstraints; height = Dimension.fillToConstraints }
        constrain(clickBlockBehindBottom)   { start.linkTo(parent.start); end.linkTo(parent.end); top.linkTo(imgLike.top); bottom.linkTo(reviewImages.bottom); width = Dimension.fillToConstraints; height = Dimension.fillToConstraints }
    }
}

@Composable
fun rememberFeedItemState(
): FeedItemState {
    return rememberSaveable(saver = DefaultFeedItemState.Saver) { DefaultFeedItemState() }
}

@Preview(showBackground = true, backgroundColor = 0xFFFDFDF6)
@Composable
fun PreviewFeed() {
    FeedItem(/* Preview */
        uiState = FeedItemUiState.Sample
    )
}
// @formatter:on