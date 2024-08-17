package com.sarang.torang.compose.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.sarang.torang.R
import com.sarang.torang.compose.component.AndroidViewRatingBar
import com.sarang.torang.compose.feed.internal.components.Comment
import com.sarang.torang.compose.feed.internal.components.CommentImage
import com.sarang.torang.compose.feed.internal.components.FavoriteImage
import com.sarang.torang.compose.feed.internal.components.ImagePagerWithIndicator
import com.sarang.torang.compose.feed.internal.components.LikeImage
import com.sarang.torang.compose.feed.internal.components.PagerIndicator
import com.sarang.torang.compose.feed.internal.components.ShareImage
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import com.sarang.torang.data.basefeed.Review
import com.sarang.torang.data.basefeed.formatedDate
import com.sarang.torang.data.basefeed.testReviewData
import com.sryang.library.ExpandableText

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
 */
@Composable
fun Feed(
    review: Review,
    isZooming: ((Boolean) -> Unit),
    progressTintColor: Color = Color(0xffe6cc00),
    favoriteColor: Color = Color(0xffe6cc00),
    onImage: ((Int) -> Unit),
    onProfile: (() -> Unit),
    onLike: (() -> Unit),
    onComment: (() -> Unit),
    onShare: (() -> Unit),
    onFavorite: (() -> Unit),
    onMenu: (() -> Unit),
    onName: (() -> Unit),
    onRestaurant: (() -> Unit),
    onLikes: (() -> Unit),
    imageLoadCompose: @Composable (Modifier, String, Dp?, Dp?, ContentScale?) -> Unit,
) {
    // @formatter:off
    val pagerState: PagerState = rememberPagerState { review.reviewImages.size }
    Column {
        ConstraintLayout(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), constraintSet = feedCommentsConstraint())
        {
            // 프로필 이미지
            imageLoadCompose.invoke(Modifier.layoutId("refProfile").testTag("imgProfile").size(32.dp).nonEffectclickable(onProfile).border(width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)).clip(RoundedCornerShape(20.dp)), review.user.profilePictureUrl, 20.dp, 20.dp, ContentScale.Crop)

            // 사용자명
            Text(modifier = Modifier.widthIn(0.dp, 150.dp).layoutId("refName").testTag("txtName").nonEffectclickable(onName), text = review.user.name, overflow = TextOverflow.Ellipsis, maxLines = 1)

            // 평점
            AndroidViewRatingBar(Modifier.layoutId("ratingBar"), review.rating, isSmall = true, changable = false, progressTintColor = progressTintColor)

            // 음식점명
            if (review.restaurant.restaurantName.isNotEmpty()) {
                Text(modifier = Modifier.widthIn(0.dp, 250.dp).layoutId("refRestaurantName").testTag("txtRestaurantName").nonEffectclickable(onRestaurant), text = review.restaurant.restaurantName, overflow = TextOverflow.Ellipsis, maxLines = 1)
            }

            // 메뉴
            IconButton(modifier = Modifier.layoutId("menu").testTag("btnMenu"), onClick = onMenu) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "menu", modifier = Modifier.background(Color.Transparent))
            }

            // 이미지 페이저
            if (review.reviewImages.isNotEmpty()) {
                ImagePagerWithIndicator(modifier = Modifier.layoutId("reviewImages"), images = review.reviewImages, onImage = onImage, isZooming = isZooming, pagerState = pagerState, image = imageLoadCompose)
            }

            PagerIndicator(modifier = Modifier.layoutId("indicator"), pagerState = pagerState, count = review.reviewImages.size)

            // 좋아요 아이콘
            LikeImage(modifier = Modifier.layoutId("heart").size(42.dp), isLike = review.isLike, onLike = onLike)

            // 코멘트 아이콘
            CommentImage(modifier = Modifier.layoutId("comment").testTag("btnComment"), onComment = onComment, size = 42.dp, padding = 9.dp)

            // 공유 아이콘
            ShareImage(modifier = Modifier.layoutId("share").testTag("btnShare"), onShare = onShare, size = 42.dp, padding = 9.dp)

            // 즐겨찾기 아이콘
            FavoriteImage(modifier = Modifier.layoutId("favorite").testTag("btnFavorite"), isFavorite = review.isFavorite, onFavorite = onFavorite, size = 42.dp, padding = 11.dp, color = favoriteColor)

            // 리뷰 내용
            if (review.contents.isNotEmpty()) {
               ExpandableText(modifier = Modifier.layoutId("contents").testTag("txtContents"),nickName = review.user.name, text = "${review.contents}", onClickNickName = { onProfile.invoke() })
            }

            // 좋아요 갯수
            if (review.likeAmount > 0) {
                Text(modifier = Modifier.layoutId("likeCount").testTag("txtLikes").clickable { onLikes.invoke() }, text = stringResource(id = R.string.like, review.likeAmount), color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontWeight = FontWeight.Bold)
            }

            // 코멘트
            Comment(Modifier.layoutId("comments"), review.comments)

            // 코멘트 갯수
            if (review.commentAmount > 0) {
                Text(modifier = Modifier.layoutId("commentCount").clickable { onComment.invoke() }, text = stringResource(id = R.string.comments, review.commentAmount), color = Color.Gray, fontWeight = W500, fontSize = 14.sp)
            }

            // 날짜
            Text(modifier = Modifier.layoutId("date").testTag("txtDate"), text = review.formatedDate(), color = Color.Gray, fontWeight = W500, fontSize = 12.sp)
        }
    }
    // @formatter:on
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
        val images = createRefFor("reviewImages")
        val profile = createRefFor("refProfile")
        val refName = createRefFor("refName")
        val restaurantName = createRefFor("refRestaurantName")
        val menu = createRefFor("menu")
        val ratingBar = createRefFor("ratingBar")
        val indicator = createRefFor("indicator")
        val date = createRefFor("date")

        val barrier = createBottomBarrier(profile, refName, ratingBar, menu)

        constrain(profile) {
            top.linkTo(parent.top)
            start.linkTo(parent.start, margin = 12.dp)
        }
        constrain(refName) {
            top.linkTo(parent.top)
            bottom.linkTo(restaurantName.top)
            start.linkTo(profile.end, margin = 8.dp)
        }
        constrain(restaurantName) {
            top.linkTo(refName.bottom)
            bottom.linkTo(profile.bottom)
            start.linkTo(profile.end, margin = 8.dp)
        }
        constrain(menu) {
            top.linkTo(parent.top)
            bottom.linkTo(profile.bottom)
            end.linkTo(parent.end)
        }
        constrain(ratingBar) {
            start.linkTo(refName.end, margin = 3.dp)
            top.linkTo(refName.top)
            bottom.linkTo(refName.bottom)
        }

        constrain(images) {
            top.linkTo(barrier, margin = 8.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(heart) {
            top.linkTo(images.bottom)
            start.linkTo(parent.start, margin = 6.dp)
        }

        constrain(comment) {
            top.linkTo(heart.top)
            start.linkTo(heart.end)
        }

        constrain(share) {
            top.linkTo(heart.top)
            start.linkTo(comment.end)
        }

        constrain(favorite) {
            top.linkTo(heart.top)
            end.linkTo(parent.end)
        }

        constrain(contents) {
            top.linkTo(likeCount.bottom)
            start.linkTo(heart.start)
            end.linkTo(favorite.end)
            width = Dimension.fillToConstraints
        }
        constrain(likeCount) {
            top.linkTo(heart.bottom)
            start.linkTo(heart.start)
        }
        constrain(comments) {
            top.linkTo(contents.bottom, 3.dp)
            start.linkTo(heart.start)
            end.linkTo(favorite.end)
            width = Dimension.fillToConstraints
        }
        constrain(date) {
            top.linkTo(commentCount.bottom)
            start.linkTo(heart.start)
        }
        constrain(commentCount) {
            start.linkTo(heart.start)
            top.linkTo(comments.bottom)
        }
        constrain(indicator) {
            top.linkTo(heart.top)
            bottom.linkTo(heart.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.fillToConstraints
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreViewFeed() {
    val data = testReviewData()
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Feed(/* Preview */
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
            ),
            imageLoadCompose = { modifier, _, w, h, _ ->
                Image(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.default_profile_icon),
                    contentDescription = "",
                )
            },
            onImage = {},
            onMenu = {},
            onProfile = {},
            onLike = {},
            onComment = {},
            onShare = {},
            onFavorite = {},
            onName = {},
            isZooming = {},
            onRestaurant = {},
            onLikes = {}
        )
    }
}
