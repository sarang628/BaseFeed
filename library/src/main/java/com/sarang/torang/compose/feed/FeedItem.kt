package com.sarang.torang.compose.feed

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
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
import androidx.compose.ui.tooling.preview.Preview
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
import com.sarang.torang.compose.feed.internal.components.ImagePagerWithIndicator
import com.sarang.torang.compose.feed.internal.components.Like
import com.sarang.torang.compose.feed.internal.components.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.components.PagerIndicator
import com.sarang.torang.compose.feed.internal.components.Share
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.basefeed.Sample
import com.sarang.torang.data.basefeed.empty
import com.sarang.torang.data.basefeed.formatedDate
import kotlinx.coroutines.flow.distinctUntilChanged

// @formatter:off
/**
 * Feed 항목
 * @param uiState            리뷰 데이터
 * @param progressTintColor ratingBar 색
 * @param favoriteColor     즐겨찾기 색
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
 * @param onPage            페이지 콜백 Int: 현재 페이지, Boolean: 첫번째 페이지 여부, Boolean: 마지막 페이지 여부
 */
@Composable
fun FeedItem(
    tag                 : String                    = "__Feed",
    uiState             : FeedItemUiState           = FeedItemUiState.empty,
    progressTintColor   : Color                     = Color(0xffe6cc00),
    favoriteColor       : Color                     = Color(0xffe6cc00),
    pageScrollAble      : Boolean                   = true,
    onLike              : () -> Unit                = { Log.w(tag, "onLike callback is not set") },
    onProfile           : () -> Unit                = { Log.w(tag, "onProfile callback is not set") },
    onComment           : () -> Unit                = { Log.w(tag, "onComment callback is not set") },
    onShare             : () -> Unit                = { Log.w(tag, "onShare callback is not set") },
    onMenu              : () -> Unit                = { Log.w(tag, "onMenu callback is not set") },
    onFavorite          : () -> Unit                = { Log.w(tag, "onFavorite callback is not set") },
    onName              : () -> Unit                = { Log.w(tag, "onName callback is not set") },
    onRestaurant        : () -> Unit                = { Log.w(tag, "onRestaurant callback is not set") },
    onLikes             : () -> Unit                = { Log.w(tag, "onLikes callback is not set") },
    onImage             : (Int) -> Unit             = { Log.w(tag, "onImage callback is not set") },
    onPage              : (Int, Boolean, Boolean) -> Unit = { page, isFirst, isLast -> Log.w(tag, "onPage callback is not set page: $page isFirst: $isFirst isLast: $isLast") }
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth(), constraintSet = feedItemConstraintSet(uiState.likeAmount > 0, uiState.contents.isNotEmpty(), uiState.commentAmount > 0, uiState.restaurantName.isNotEmpty())) {
        ImagePagerWithIndicator (modifier = Modifier.layoutId("reviewImages")   , images = uiState.reviewImages, showIndicator = true, onImage = onImage, height = with(LocalDensity.current) { uiState.height.toDp() }, scrollEnable = pageScrollAble)
        UserName                (modifier = Modifier.layoutId("txtName")        , userName = uiState.userName, onName = onName)
        AndroidViewRatingBar    (modifier = Modifier.layoutId("ratingBar")      , rating = uiState.rating, progressTintColor = progressTintColor)
        RestaurantName          (modifier = Modifier.layoutId("restaurantName") , restaurantNeme = uiState.restaurantName, onRestaurant = onRestaurant)
        Menu                    (modifier = Modifier.layoutId("menu")           , onMenu = onMenu)
        LikeCount               (modifier = Modifier.layoutId("likeCount")      , count = uiState.likeAmount, onLikes = onLike)
        Comment                 (modifier = Modifier.layoutId("comments")       , comments = uiState.comments)
        CommentCount            (modifier = Modifier.layoutId("commentCount")   , count = uiState.commentAmount, onComment)
        Date                    (modifier = Modifier.layoutId("date")           , date = uiState.formatedDate())
        Like                    (modifier = Modifier.layoutId("imgLike")        , isLike = uiState.isLike, onLike = onLike, animation = uiState.isLogin)
        Comment                 (modifier = Modifier.layoutId("imgComment")     , onComment = onComment)
        Share                   (modifier = Modifier.layoutId("imgShare")       , onShare = onShare)
        Favorite                (modifier = Modifier.layoutId("imgFavorite")    , isFavorite = uiState.isFavorite, onFavorite = onFavorite, color = favoriteColor)
        ProfileImage            (modifier = Modifier.layoutId("imgProfile")     , url = uiState.profilePictureUrl, onProfile = onProfile)
        Contents                (modifier = Modifier.layoutId("contents")       , userName = uiState.userName, contents = uiState.contents, onProfile = onProfile)
    }
}

@Composable
fun Contents(modifier : Modifier = Modifier, userName : String = "", contents : String, onProfile : ()->Unit = {}){
    LocalExpandableTextType.current.invoke(modifier.testTag("txtContents"), userName, contents, onProfile)
}

@Composable
fun ProfileImage(modifier : Modifier, onProfile: () -> Unit = {}, url : String = ""){
    LocalFeedImageLoader.current.invoke(modifier.testTag("imgProfile").size(32.dp).nonEffectclickable(onProfile).border(width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)).clip(RoundedCornerShape(20.dp)), url, 20.dp, 20.dp, ContentScale.Crop, null)
}

@Composable
fun UserName(modifier : Modifier = Modifier, onName: () -> Unit = {}, userName : String = ""){
    Text(modifier = modifier.widthIn(0.dp, 150.dp).nonEffectclickable(onName),
        text = userName, overflow = TextOverflow.Ellipsis, maxLines = 1, color = Color.White, style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
}

@Composable
fun Date(modifier : Modifier = Modifier, date : String = ""){
    Text(modifier = modifier.testTag("txtDate"), text = date, color = Color.Gray, fontWeight = W500, fontSize = 12.sp)
}

@Composable
fun CommentCount(modifier : Modifier = Modifier, count : Int, onComment : ()->Unit = {}){
    Text(modifier = modifier.clickable { onComment.invoke() }, text = stringResource(id = R.string.comments, count), color = Color.Gray, fontWeight = W500, fontSize = 14.sp)
}

@Composable
fun LikeCount(modifier : Modifier = Modifier, onLikes : ()->Unit = {}, count : Int = 0){
    Text(modifier = modifier.testTag("txtLikes").clickable { onLikes.invoke() }, text = stringResource(id = R.string.like, count), color = if (isSystemInDarkTheme()) Color.White else Color.White, fontWeight = FontWeight.Bold)
}

@Composable
fun Menu(modifier : Modifier = Modifier, onMenu : ()->Unit){
    IconButton(modifier = modifier.testTag("btnMenu"), onClick = onMenu) {
        Icon(imageVector = Icons.Default.MoreVert, tint = Color.White, contentDescription = "menu", modifier = Modifier.background(Color.Transparent))
    }
}

@Composable
fun RestaurantName(modifier : Modifier, onRestaurant : ()->Unit, restaurantNeme : String = ""){
    Text(modifier = modifier.testTag("txtRestaurantName").widthIn(0.dp, 250.dp).nonEffectclickable(onRestaurant),
        text = restaurantNeme, overflow = TextOverflow.Ellipsis, maxLines = 1, color = Color.White, style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
}



fun feedItemConstraintSet(visibleLike : Boolean = false, visibieContents : Boolean = false, visibleCommentCount : Boolean = false, visibleRestaurantName : Boolean = false) : ConstraintSet{
    return ConstraintSet{
        val likeCount       = createRefFor("likeCount")
        val reviewImages    = createRefFor("reviewImages")
        val contents        = createRefFor("contents")
        val comments        = createRefFor("comments")
        val commentCount    = createRefFor("commentCount")
        val date            = createRefFor("date")
        val imgLike         = createRefFor("imgLike")
        val imgComment      = createRefFor("imgComment")
        val imgShare        = createRefFor("imgShare")
        val indicator       = createRefFor("indicator")
        val imgFavorite     = createRefFor("imgFavorite")
        val imgProfile      = createRefFor("imgProfile")
        val txtName         = createRefFor("txtName")
        val ratingBar       = createRefFor("ratingBar")
        val restaurantName  = createRefFor("restaurantName")
        val menu            = createRefFor("menu")
        val guideline       = createBottomBarrier(reviewImages, margin = 4.dp)

        constrain(reviewImages) { top.linkTo(parent.top) }
        constrain(likeCount)    { bottom.linkTo(imgLike.top); start.linkTo(reviewImages.start, 8.dp); visibility = if(visibleLike) Visibility.Visible else Visibility.Gone }
        constrain(commentCount) { start.linkTo(parent.start, 4.dp); top.linkTo(guideline); visibility = if(visibleCommentCount) Visibility.Visible else Visibility.Gone}
        constrain(contents)     { start.linkTo(parent.start, 4.dp); end.linkTo(parent.end, 4.dp); top.linkTo(commentCount.bottom); visibility = if(visibieContents) Visibility.Visible else Visibility.Gone; width = Dimension.fillToConstraints }
        constrain(comments)     { top.linkTo(contents.bottom); visibility = if(visibleCommentCount) Visibility.Visible else Visibility.Gone }
        constrain(date)         { start.linkTo(parent.start, 4.dp); top.linkTo(comments.bottom, 8.dp); }

        constrain(imgLike)      { start.linkTo(reviewImages.start); bottom.linkTo(reviewImages.bottom); }
        constrain(imgComment)   { start.linkTo(imgLike.end); bottom.linkTo(reviewImages.bottom); }
        constrain(imgShare)     { start.linkTo(imgComment.end); bottom.linkTo(reviewImages.bottom); }
        constrain(indicator)    { start.linkTo(reviewImages.start); end.linkTo(reviewImages.end); top.linkTo(imgLike.top); bottom.linkTo(imgLike.bottom); }
        constrain(imgFavorite)  { end.linkTo(reviewImages.end); bottom.linkTo(reviewImages.bottom) }

        constrain(imgProfile)   { top.linkTo(reviewImages.top, 4.dp); start.linkTo(reviewImages.start, 4.dp) }
        constrain(txtName)      { start.linkTo(imgProfile.end, 4.dp); top.linkTo(imgProfile.top); bottom.linkTo(restaurantName.top) }
        constrain(ratingBar)    { start.linkTo(txtName.end, 4.dp); top.linkTo(txtName.top); bottom.linkTo(txtName.bottom) }
        constrain(restaurantName){ top.linkTo(txtName.bottom); bottom.linkTo(imgProfile.bottom); start.linkTo(imgProfile.end, 4.dp) }
        constrain(menu)         { end.linkTo(parent.end) }
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