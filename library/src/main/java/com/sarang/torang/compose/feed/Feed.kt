package com.sarang.torang.compose.feed

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.sarang.torang.R
import com.sarang.torang.compose.component.AndroidViewRatingBar
import com.sarang.torang.compose.feed.internal.components.Comment
import com.sarang.torang.compose.feed.internal.components.CommentImage
import com.sarang.torang.compose.feed.internal.components.FavoriteImage
import com.sarang.torang.compose.feed.internal.components.ImagePagerWithIndicator
import com.sarang.torang.compose.feed.internal.components.LikeImage
import com.sarang.torang.compose.feed.internal.components.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.components.PagerIndicator
import com.sarang.torang.compose.feed.internal.components.ShareImage
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import com.sarang.torang.data.basefeed.Review
import com.sarang.torang.data.basefeed.User
import com.sarang.torang.data.basefeed.formatedDate
import com.sarang.torang.data.basefeed.testReviewData
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Feed 항목
 * @param review 리뷰 데이터
 * @param isZooming pinch zoom 여부
 * @param progressTintColor ratingBar 색
 * @param favoriteColor 즐겨찾기 색
 * @param onImage 이미지 클릭
 * @param onProfile 프로필 클릭
 * @param onLike 좋아요 클릭
 * @param onComment 코멘트 클릭
 * @param onShare 공유 클릭
 * @param onFavorite 즐겨찾기 클릭
 * @param onMenu 메뉴 클릭
 * @param onName 사용자명 클릭
 * @param onRestaurant 음식점명 클릭
 * @param onLikes 좋아요 클릭
 * @param imageLoadCompose 공통 이미지 compose
 * @param expandableText 공통 펼쳐보기 compose
 * @param isLogin 로그인 여부
 * @param videoPlayer 비디오 플레이어
 * @param imageHeight 이미지 높이
 * @param onPressed 터치 시작
 * @param onReleased 터치 끝
 * @param onPage 페이지 콜백 Int: 현재 페이지, Boolean: 첫번째 페이지 여부, Boolean: 마지막 페이지 여부
 */
@Composable
fun Feed(
    tag: String = "__Feed",
    review: Review,
    isZooming: ((Boolean) -> Unit) = {},
    progressTintColor: Color = Color(0xffe6cc00),
    favoriteColor: Color = Color(0xffe6cc00),
    onImage: ((Int) -> Unit) = { Log.w(tag, "onImage callback is not set") },
    onProfile: (() -> Unit) = { Log.w(tag, "onProfile callback is not set") },
    onLike: (() -> Unit) = { Log.w(tag, "onLike callback is not set") },
    onComment: (() -> Unit) = { Log.w(tag, "onComment callback is not set") },
    onShare: (() -> Unit) = { Log.w(tag, "onShare callback is not set") },
    onFavorite: (() -> Unit) = { Log.w(tag, "onFavorite callback is not set") },
    onMenu: (() -> Unit) = { Log.w(tag, "onMenu callback is not set") },
    onName: (() -> Unit) = { Log.w(tag, "onName callback is not set") },
    onRestaurant: (() -> Unit) = { Log.w(tag, "onRestaurant callback is not set") },
    onLikes: (() -> Unit) = { Log.w(tag, "onLikes callback is not set") },
    expandableText: @Composable (Modifier, String, String, () -> Unit) -> Unit = { _, _, _, _ -> },
    isLogin: Boolean = false,
    videoPlayer: @Composable (String) -> Unit = {},
    imageHeight: Dp = 400.dp,
    onPressed: () -> Unit = {},
    onReleased: () -> Unit = {},
    onPage: (Int, Boolean, Boolean) -> Unit = { _, _, _ -> },
    pageScrollAble: Boolean = true,
    topInImage: Boolean = true
) {
    // @formatter:off
    val pagerState: PagerState = rememberPagerState { review.reviewImages.size }

    LaunchedEffect(pagerState) {
        snapshotFlow{pagerState.currentPage}
            .distinctUntilChanged() // 중복된 값 방지
            .collect{ onPage.invoke(it, it == 0, it == review.reviewImages.size - 1) }
    }

    Column(modifier = Modifier.fillMaxWidth())
    {
        val topCompose: @Composable () -> Unit = {
            FeedTop(rating = review.rating, restaurantName1 = review.restaurant.restaurantName, profilePictureUrl = review.user.profilePictureUrl, name1 = review.user.name, onProfile = onProfile, onRestaurant = onRestaurant, onName = onName, onMenu = onMenu)
        }

        if(!topInImage) topCompose()

        if (review.reviewImages.isNotEmpty()) { // 이미지 페이저
            Box()
            {
                ImagePagerWithIndicator(modifier = Modifier.layoutId("reviewImages"), images = review.reviewImages, onImage = onImage, pagerState = pagerState, videoPlayer = videoPlayer, height = imageHeight, onPressed = onPressed, onReleased = onReleased, scrollEnable = pageScrollAble)
                if(topInImage) topCompose()
                Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                    if (review.likeAmount > 0) { // 좋아요 갯수
                        Text(modifier = Modifier.layoutId("likeCount").testTag("txtLikes").clickable { onLikes.invoke() }, text = stringResource(id = R.string.like, review.likeAmount), color = if (isSystemInDarkTheme()) Color.White else Color.White, fontWeight = FontWeight.Bold)
                    }

                    FeedBottom(pagerState, review.reviewImages.size, onShare, onComment, onFavorite, onLike, isLogin, review.isFavorite, review.isLike, favoriteColor)
                }
            }
        }

        if (review.contents.isNotEmpty()) { // 리뷰 내용
            Spacer(Modifier.height(8.dp))
            expandableText.invoke(Modifier.layoutId("contents").testTag("txtContents"), review.user.name, review.contents, onProfile)
        }



        // 코멘트
        Comment(Modifier.layoutId("comments"), review.comments)

        if (review.commentAmount > 0) { // 코멘트 갯수
            Text(modifier = Modifier.layoutId("commentCount").clickable { onComment.invoke() }, text = stringResource(id = R.string.comments, review.commentAmount), color = Color.Gray, fontWeight = W500, fontSize = 14.sp)
        }

        // 날짜
        Text(modifier = Modifier.layoutId("date").testTag("txtDate"), text = review.formatedDate(), color = Color.Gray, fontWeight = W500, fontSize = 12.sp)
    }
    // @formatter:on
}

@Composable
fun FeedBottom(pagerState: PagerState, imageSize : Int, onShare: () -> Unit, onComment: () -> Unit, onFavorite: () -> Unit, onLike: () -> Unit, isLogin : Boolean, isFavorite : Boolean, isLike : Boolean, favoriteColor : Color){
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (imgLike, imgComment, imgShare, indicator, imgFavorite) = createRefs()

        // 좋아요 아이콘
        LikeImage(modifier = Modifier
            .size(42.dp)
            .constrainAs(imgLike) {}, isLike = isLike, onLike = onLike, animation = isLogin)

        // 코멘트 아이콘
        CommentImage(modifier = Modifier
            .constrainAs(imgComment) { start.linkTo(imgLike.end) }
            .testTag("btnComment"), onComment = onComment, size = 42.dp, padding = 9.dp)

        // 공유 아이콘
        ShareImage(modifier = Modifier
            .constrainAs(imgShare) { start.linkTo(imgComment.end) }
            .testTag("btnShare"), onShare = onShare, size = 42.dp, padding = 9.dp)

        PagerIndicator(modifier = Modifier
            .constrainAs(indicator) {
                start.linkTo(parent.start); end.linkTo(parent.end); top.linkTo(parent.top); bottom.linkTo(
                parent.bottom
            )
            }
            .fillMaxWidth(), pagerState = pagerState, count = imageSize)

        // 즐겨찾기 아이콘
        FavoriteImage(modifier = Modifier
            .constrainAs(imgFavorite) { end.linkTo(parent.end) }
            .testTag("btnFavorite"), isFavorite = isFavorite, onFavorite = onFavorite, size = 42.dp, padding = 11.dp, color = favoriteColor)
    }
}

@Composable
fun FeedTop(
    progressTintColor: Color = Color(0xffe6cc00),
    tag: String = "__FeedTop",
    rating: Float = 0f,
    restaurantName1: String = "",
    profilePictureUrl: String = "",
    name1: String = "",
    onProfile: (() -> Unit) = { Log.w(tag, "onProfile callback is not set") },
    onRestaurant: (() -> Unit) = { Log.w(tag, "onRestaurant callback is not set") },
    onName: (() -> Unit) = { Log.w(tag, "onName callback is not set") },
    onMenu: (() -> Unit) = { Log.w(tag, "onMenu callback is not set") },
) {
    ConstraintLayout(Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
        val (imgProfile, name, ratingBar, restaurantName, menu) = createRefs()
        createVerticalChain(name, restaurantName, chainStyle = ChainStyle.Packed)
        // 프로필 이미지
        LocalFeedImageLoader.current.invoke(
            Modifier.constrainAs(imgProfile) { top.linkTo(parent.top); bottom.linkTo(parent.bottom) }
                .testTag("imgProfile").size(32.dp).nonEffectclickable(onProfile).border(width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)).clip(RoundedCornerShape(20.dp)),
            profilePictureUrl, 20.dp, 20.dp, ContentScale.Crop, null)

        // 사용자 명
        Text(modifier = Modifier.constrainAs(name) { start.linkTo(imgProfile.end, 4.dp); top.linkTo(imgProfile.top); bottom.linkTo(restaurantName.top) }.widthIn(0.dp, 150.dp).testTag("txtName").layoutId("txtName").nonEffectclickable(onName),
            text = name1, overflow = TextOverflow.Ellipsis, maxLines = 1, color = Color.White, style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))

        // 평점
        AndroidViewRatingBar(Modifier.constrainAs(ref = ratingBar) { start.linkTo(name.end, 4.dp); top.linkTo(name.top); bottom.linkTo(name.bottom) }, rating, isSmall = true, changable = false, progressTintColor = progressTintColor)

        if (restaurantName1.isNotEmpty()) { // 음식점 명
            Text(modifier = Modifier.widthIn(0.dp, 250.dp).constrainAs(restaurantName) { top.linkTo(name.bottom); bottom.linkTo(imgProfile.bottom); start.linkTo(imgProfile.end, 4.dp) }.testTag("txtRestaurantName")
                .nonEffectclickable(onRestaurant), text = restaurantName1, overflow = TextOverflow.Ellipsis, maxLines = 1, color = Color.White, style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)))
        }

        IconButton(modifier = Modifier.constrainAs(menu) { end.linkTo(parent.end) }.testTag("btnMenu"), onClick = onMenu) { // 메뉴
            Icon(imageVector = Icons.Default.MoreVert, tint = Color.White, contentDescription = "menu", modifier = Modifier.background(Color.Transparent))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeed() {
    val data = testReviewData()
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Feed(
            /* Preview */
            review = data.copy(
                user =
                    data.user.copy(
                        name = "namenamenamenamename",
                        profilePictureUrl = "https://wimg.mk.co.kr/news/cms/202304/14/news-p.v1.20230414.15e6ac6d76a84ab398281046dc858116_P1.jpg"
                    ),
                //restaurant = data.restaurant.copy(restaurantName = "YourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDining"),
                restaurant = data.restaurant.copy(restaurantName = "restaurants"),
                likeAmount = 10,
                isLike = false,
                isFavorite = false,
                createDate = "2022-10-10 10:10:10",
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewFeedTop() {
    FeedTop(
        name1 = "name",
        restaurantName1 = "restaurant",
        rating = 3.5f
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewFeedBottom(){
    val pagerState: PagerState = rememberPagerState { 10 }
    FeedBottom(pagerState, 10, {}, {}, {}, {}, false, false, false, Color.White)
}

fun imageLoadCompose(): @Composable (Modifier, String, Dp?, Dp?, ContentScale?, Dp?) -> Unit =
    { modifier, _, w, h, _, _ ->
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.default_profile_icon),
            contentDescription = "",
        )
    }

