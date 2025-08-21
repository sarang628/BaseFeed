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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.sarang.torang.R
import com.sarang.torang.compose.component.AndroidViewRatingBar
import com.sarang.torang.compose.feed.internal.components.Comment
import com.sarang.torang.compose.feed.internal.components.CommentImage
import com.sarang.torang.compose.feed.internal.components.FavoriteImage
import com.sarang.torang.compose.feed.internal.components.ImagePagerWithIndicator
import com.sarang.torang.compose.feed.internal.components.LikeImage
import com.sarang.torang.compose.feed.internal.components.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.components.PagerIndicator
import com.sarang.torang.compose.feed.internal.components.ShareImage
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.basefeed.Sample
import com.sarang.torang.data.basefeed.empty
import com.sarang.torang.data.basefeed.formatedDate
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Feed 항목
 * @param uiState            리뷰 데이터
 * @param isZooming         pinch zoom 여부
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
 * @param isLogin           로그인 여부
 * @param imageHeight       이미지 높이
 * @param onPressed         터치 시작
 * @param onReleased        터치 끝
 * @param onPage            페이지 콜백 Int: 현재 페이지, Boolean: 첫번째 페이지 여부, Boolean: 마지막 페이지 여부
 */
@Composable
fun FeedItem(
    tag                 : String          = "__Feed",
    uiState             : FeedItemUiState = FeedItemUiState.empty,
    //feedItemState       : FeedItemState = rememberFeedItemState(),
    progressTintColor   : Color           = Color(0xffe6cc00),
    favoriteColor       : Color           = Color(0xffe6cc00),
    isLogin             : Boolean         = false,
    pageScrollAble      : Boolean         = true,
    imageHeight         : Dp              = 400.dp,
    onPressed           : () -> Unit      = { Log.w(tag, "onPressed callback is not set") },
    onReleased          : () -> Unit      = { Log.w(tag, "onReleased callback is not set") },
    onLike              : () -> Unit      = { Log.w(tag, "onLike callback is not set") },
    onProfile           : () -> Unit      = { Log.w(tag, "onProfile callback is not set") },
    onComment           : () -> Unit      = { Log.w(tag, "onComment callback is not set") },
    onShare             : () -> Unit      = { Log.w(tag, "onShare callback is not set") },
    onMenu              : () -> Unit      = { Log.w(tag, "onMenu callback is not set") },
    onFavorite          : () -> Unit      = { Log.w(tag, "onFavorite callback is not set") },
    onName              : () -> Unit      = { Log.w(tag, "onName callback is not set") },
    onRestaurant        : () -> Unit      = { Log.w(tag, "onRestaurant callback is not set") },
    onLikes             : () -> Unit      = { Log.w(tag, "onLikes callback is not set") },
    onImage             : (Int) -> Unit   = { Log.w(tag, "onImage callback is not set") },
    isZooming           : (Boolean) -> Unit       = { Log.w(tag, "isZooming callback is not set") },
    onPage              : (Int, Boolean, Boolean) -> Unit = { _, _, _ -> Log.w(tag, "onProfile callback is not set") }
) {
    // @formatter:off
    val pagerState: PagerState = rememberPagerState { uiState.reviewImages.size }

    LaunchedEffect(pagerState) {
        snapshotFlow{pagerState.currentPage}
            .distinctUntilChanged() // 중복된 값 방지
            .collect{ onPage.invoke(it, it == 0, it == uiState.reviewImages.size - 1) }
    }

    ConstraintLayout(modifier = Modifier.fillMaxWidth(), constraintSet = feedItemConstraintSet(uiState.likeAmount > 0, uiState.contents.isNotEmpty(), uiState.commentAmount > 0, uiState.restaurantName.isNotEmpty())) {
        // 이미지 페이저
        ImagePagerWithIndicator(modifier = Modifier.
        layoutId("reviewImages"), images = uiState.reviewImages, onImage = onImage, pagerState = pagerState, height = imageHeight, onPressed = onPressed, onReleased = onReleased, scrollEnable = pageScrollAble)
        // 프로필 이미지
        LocalFeedImageLoader.current.invoke(Modifier.testTag("imgProfile").size(32.dp).nonEffectclickable(onProfile).border(width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)).clip(RoundedCornerShape(20.dp))
            .layoutId("imgProfile"), uiState.profilePictureUrl, 20.dp, 20.dp, ContentScale.Crop, null)
        // 사용자 명
        Text(modifier = Modifier.widthIn(0.dp, 150.dp).testTag("txtName").nonEffectclickable(onName)
            .layoutId("txtName"), text = uiState.userName, overflow = TextOverflow.Ellipsis, maxLines = 1, color = Color.White, style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
        // 평점
        AndroidViewRatingBar(Modifier
            .layoutId("ratingBar") , rating = uiState.rating, isSmall = true, changable = false, progressTintColor = progressTintColor)
        // 음식점 명
        Text(modifier = Modifier.widthIn(0.dp, 250.dp).testTag("txtRestaurantName").nonEffectclickable(onRestaurant)
            .layoutId("restaurantName"), text = uiState.restaurantName, overflow = TextOverflow.Ellipsis, maxLines = 1, color = Color.White, style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
        // 메뉴 버튼
        IconButton(modifier = Modifier.testTag("btnMenu")
            .layoutId("menu"), onClick = onMenu) {
            Icon(imageVector = Icons.Default.MoreVert, tint = Color.White, contentDescription = "menu", modifier = Modifier.background(Color.Transparent))
        }
        // 좋아요 갯수
        Text(modifier = Modifier.
            layoutId("likeCount").testTag("txtLikes").clickable { onLikes.invoke() }, text = stringResource(id = R.string.like, uiState.likeAmount), color = if (isSystemInDarkTheme()) Color.White else Color.White, fontWeight = FontWeight.Bold)
        // 피드 내용
        LocalExpandableTextType.current.invoke(Modifier.
            layoutId("contents").testTag("txtContents"), uiState.userName, uiState.contents, onProfile)
        // 코멘트
        Comment(modifier = Modifier.
            layoutId("comments"), uiState.comments)
        // 코멘트 갯수
        Text(modifier = Modifier.
            layoutId("commentCount").clickable { onComment.invoke() }, text = stringResource(id = R.string.comments, uiState.commentAmount), color = Color.Gray, fontWeight = W500, fontSize = 14.sp)
        // 날짜
        Text(modifier = Modifier.
            layoutId("date").testTag("txtDate"), text = uiState.formatedDate(), color = Color.Gray, fontWeight = W500, fontSize = 12.sp)
        // 좋아요 아이콘
        LikeImage(modifier = Modifier.size(42.dp)
            .layoutId("imgLike") , isLike = uiState.isLike, onLike = onLike, animation = isLogin)
        // 코멘트 아이콘
        CommentImage(modifier = Modifier.testTag("btnComment")
            .layoutId("imgComment"), onComment = onComment, size = 42.dp, padding = 9.dp)
        // 공유 아이콘
        ShareImage(modifier = Modifier.testTag("btnShare")
            .layoutId("imgShare"), onShare = onShare, size = 42.dp, padding = 9.dp)
        // 페이지 인디케이터
        PagerIndicator(modifier = Modifier
            .layoutId("indicator")
            .fillMaxWidth(), pagerState = pagerState, count = uiState.reviewImages.size)
        // 즐겨찾기 아이콘
        FavoriteImage(modifier = Modifier.testTag("btnFavorite")
            .layoutId("imgFavorite"), isFavorite = uiState.isFavorite, onFavorite = onFavorite, size = 42.dp, padding = 11.dp, color = favoriteColor)
    }
    // @formatter:on
}

fun feedItemConstraintSet(visibleLike : Boolean = false, visibieContents : Boolean = false, visibleCommentCount : Boolean = false, visibleRestaurantName : Boolean = false) : ConstraintSet{
    return ConstraintSet{
        val likeCount = createRefFor("likeCount")
        val reviewImages = createRefFor("reviewImages")
        val contents = createRefFor("contents")
        val comments = createRefFor("comments")
        val commentCount = createRefFor("commentCount")
        val date = createRefFor("date")
        val imgLike = createRefFor("imgLike")
        val imgComment = createRefFor("imgComment")
        val imgShare = createRefFor("imgShare")
        val indicator = createRefFor("indicator")
        val imgFavorite = createRefFor("imgFavorite")
        val imgProfile = createRefFor("imgProfile")
        val txtName = createRefFor("txtName")
        val ratingBar = createRefFor("ratingBar")
        val restaurantName = createRefFor("restaurantName")
        val menu = createRefFor("menu")
        val guideline = createBottomBarrier(reviewImages, margin = 4.dp)

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