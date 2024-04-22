package com.sarang.torang.compose.feed

import TorangAsyncImage
import TorangAsyncImage1
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.sarang.torang.compose.component.AndroidViewRatingBar
import com.sarang.torang.R
import com.sarang.torang.compose.feed.internal.components.Comment
import com.sarang.torang.compose.feed.internal.components.CommentImage
import com.sarang.torang.compose.feed.internal.components.ExpandableText
import com.sarang.torang.compose.feed.internal.components.FavoriteImage
import com.sarang.torang.compose.feed.internal.components.ImagePagerWithIndicator
import com.sarang.torang.compose.feed.internal.components.LikeImage
import com.sarang.torang.compose.feed.internal.components.PagerIndicator
import com.sarang.torang.compose.feed.internal.components.ShareImage
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import com.sarang.torang.data.basefeed.Review
import com.sarang.torang.data.basefeed.formatedDate
import com.sarang.torang.data.basefeed.testReviewData

/**
 * Feed 항목
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Feed(
    review: Review,
    isZooming: ((Boolean) -> Unit)? = null
) {
    val pagerState: PagerState = rememberPagerState { review.reviewImages.size }
    Column {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth(),
            constraintSet = feedCommentsConstraint()

        ) {
            // 프로필 이미지
            TorangAsyncImage1(
                modifier = Modifier
                    .layoutId("refProfile")
                    .size(32.dp)
                    .nonEffectclickable { review.onProfile?.invoke() }
                    .border(
                        width = 0.5.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clip(RoundedCornerShape(20.dp)),
                model = review.user.profilePictureUrl,
                progressSize = 20.dp,
                errorIconSize = 20.dp
            )
            // 사용자명
            Text(
                modifier = Modifier
                    .widthIn(0.dp, 150.dp)
                    .layoutId("refName")
                    .nonEffectclickable { review.onName?.invoke() },
                text = review.user.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            // 평점
            AndroidViewRatingBar(Modifier.layoutId("ratingBar"), review.rating, isSmall = true, changable = false)
            // 음식점명
            Text(
                modifier = Modifier
                    .widthIn(0.dp, 250.dp)
                    .layoutId("refRestaurantName")
                    .nonEffectclickable { review.onRestaurant?.invoke() },
                text = review.restaurant.restaurantName,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            // 메뉴
            IconButton(
                modifier = Modifier.layoutId("menu"),
                onClick = { review.onMenu?.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert, contentDescription = "menu",
                    modifier = Modifier.background(Color.Transparent)
                )
            }
            // 이미지 페이저
            if (review.reviewImages.isNotEmpty()) {
                ImagePagerWithIndicator(
                    modifier = Modifier.layoutId("reviewImages"),
                    images = review.reviewImages,
                    onImage = { review.onImage?.invoke(it) },
                    isZooming = isZooming,
                    pagerState = pagerState
                )
            }

            PagerIndicator(
                modifier = Modifier.layoutId("indicator"),
                pagerState = pagerState,
                count = review.reviewImages.size
            )

            // 좋아요 아이콘
            LikeImage(
                modifier = Modifier.layoutId("heart"),
                isLike = review.isLike,
                onLike = { review.onLike?.invoke() },
                size = 42.dp,
                padding = 9.dp
            )
            // 코멘트 아이콘
            CommentImage(
                modifier = Modifier.layoutId("comment"),
                onComment = { review.onComment?.invoke() },
                size = 42.dp,
                padding = 9.dp
            )
            // 공유 아이콘
            ShareImage(
                modifier = Modifier.layoutId("share"),
                onShare = { review.onShare?.invoke() },
                size = 42.dp,
                padding = 9.dp
            )
            // 즐겨찾기 아이콘
            FavoriteImage(
                modifier = Modifier.layoutId("favorite"),
                isFavorite = review.isFavorite,
                onFavorite = { review.onFavorite?.invoke() },
                size = 42.dp,
                padding = 9.dp
            )
            // 리뷰 내용
            if (review.contents.isNotEmpty()) {
                val annotatedString = buildAnnotatedString {
                    append("I have read")
                    withStyle(style = SpanStyle(Color.Blue)) {
                        append(" Terms and Condition")
                    }
                }
                Column(
                    modifier = Modifier.layoutId("contents"),
                ) {
                    ExpandableText(
                        nickName = review.user.name,
                        text = "${review.contents}"
                    )
                }
            }

            // 좋아요 갯수
            if (review.likeAmount > 0) {
                Text(
                    modifier = Modifier.layoutId("likeCount"),
                    text = stringResource(id = R.string.like, review.likeAmount),
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }


            // 코멘트
            Comment(Modifier.layoutId("comments"), review.comments)

            // 코멘트 갯수
            if (review.commentAmount > 0) {
                Text(
                    modifier = Modifier.layoutId("commentCount"),
                    text = stringResource(id = R.string.comments, review.commentAmount),
                    color = Color.Gray,
                    fontWeight = W500,
                    fontSize = 14.sp
                )
            }

            Text(
                modifier = Modifier.layoutId("date"),
                text = review.formatedDate(),
                color = Color.Gray,
                fontWeight = W500,
                fontSize = 12.sp
            )
        }
    }
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
        val reviewImages = createRefFor("reviewImages")
        val refProfile = createRefFor("refProfile")
        val refName = createRefFor("refName")
        val refRestaurantName = createRefFor("refRestaurantName")
        val menu = createRefFor("menu")
        val ratingBar = createRefFor("ratingBar")
        val indicator = createRefFor("indicator")
        val date = createRefFor("date")
        val guide = createGuidelineFromTop(45.dp)

        constrain(refProfile) {
            top.linkTo(parent.top)
            start.linkTo(parent.start, margin = 12.dp)
            bottom.linkTo(guide)
        }
        constrain(refName) {
            top.linkTo(refProfile.top)
            bottom.linkTo(refRestaurantName.top)
            start.linkTo(refProfile.end, margin = 8.dp)
        }
        constrain(refRestaurantName) {
            top.linkTo(refName.bottom)
            bottom.linkTo(refProfile.bottom)
            start.linkTo(refProfile.end, margin = 8.dp)
        }
        constrain(menu) {
            top.linkTo(refProfile.top)
            bottom.linkTo(refProfile.bottom)
            end.linkTo(parent.end)
        }
        constrain(ratingBar) {
            start.linkTo(refName.end, margin = 3.dp)
            top.linkTo(refName.top)
            bottom.linkTo(refName.bottom)
        }

        constrain(reviewImages) {
            top.linkTo(refProfile.bottom, margin = 8.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(heart) {
            top.linkTo(reviewImages.bottom)
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


@Preview
@Composable
fun PreViewFeed() {
    val data = testReviewData()
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Feed(/* Preview */
            review = data.copy(
                user =
                data.user.copy(
                    name = "GeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGeminiGemini",
                    profilePictureUrl = "https://wimg.mk.co.kr/news/cms/202304/14/news-p.v1.20230414.15e6ac6d76a84ab398281046dc858116_P1.jpg"
                ),
                restaurant = data.restaurant.copy(restaurantName = "YourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDiningYourFineDining"),
                likeAmount = 10,
                isLike = false,
                isFavorite = false,
                createDate = "2022-10-10 10:10:10"
            )
        )
    }
}
