package com.sarang.torang.compose.feed

import TorangAsyncImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
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
import com.sarang.torang.data.basefeed.testReviewData

/**
 * Feed 항목
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Feed(
    review: Review,
    ratingBar: @Composable (Modifier, Float) -> Unit,
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
            TorangAsyncImage(
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
                    .layoutId("refName")
                    .nonEffectclickable { review.onName?.invoke() },
                text = review.user.name,
            )
            // 평점
            ratingBar.invoke(Modifier.layoutId("refRatingBar"), review.rating)
            // 음식점명
            Text(
                modifier = Modifier
                    .layoutId("refRestaurantName")
                    .nonEffectclickable { review.onRestaurant?.invoke() },
                text = review.restaurant.restaurantName
            )
            // 메뉴
            IconButton(
                modifier = Modifier.layoutId("refMenu"),
                onClick = { review.onMenu?.invoke() }
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
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
                onLike = { review.onLike?.invoke() }
            )
            // 코멘트 아이콘
            CommentImage(
                modifier = Modifier.layoutId("comment"),
                onComment = { review.onComment?.invoke() })
            // 공유 아이콘
            ShareImage(
                modifier = Modifier.layoutId("share"),
                onShare = { review.onShare?.invoke() })
            // 즐겨찾기 아이콘
            FavoriteImage(
                modifier = Modifier.layoutId("favorite"),
                isFavorite = review.isFavorite,
                onFavorite = { review.onFavorite?.invoke() }
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
                    color = Color.Black,
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
                    fontSize = 16.sp
                )
            }

            Text(
                modifier = Modifier.layoutId("date"),
                text = "July 29, 2023",
                color = Color.Gray,
                fontSize = 13.sp
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
        val refMenu = createRefFor("refMenu")
        val refRatingBar = createRefFor("refRatingBar")
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
        constrain(refMenu) {
            top.linkTo(refProfile.top)
            bottom.linkTo(refProfile.bottom)
            end.linkTo(parent.end)
        }
        constrain(refRatingBar) {
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
            top.linkTo(reviewImages.bottom, margin = 12.dp)
            start.linkTo(parent.start, margin = 12.dp)
        }

        constrain(comment) {
            top.linkTo(heart.top)
            start.linkTo(heart.end, margin = 16.dp)
        }

        constrain(share) {
            top.linkTo(heart.top)
            start.linkTo(comment.end, margin = 16.dp)
        }

        constrain(favorite) {
            top.linkTo(heart.top)
            end.linkTo(parent.end, margin = 12.dp)
        }

        constrain(contents) {
            top.linkTo(likeCount.bottom)
            start.linkTo(heart.start)
            end.linkTo(favorite.end)
            width = Dimension.fillToConstraints
        }
        constrain(likeCount) {
            top.linkTo(heart.bottom, margin = 8.dp)
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
                    name = "Gemini",
                    profilePictureUrl = "https://wimg.mk.co.kr/news/cms/202304/14/news-p.v1.20230414.15e6ac6d76a84ab398281046dc858116_P1.jpg"
                ),
                restaurant = data.restaurant.copy(restaurantName = "YourFineDining"),
                likeAmount = 10,
                isLike = false,
                isFavorite = false,
            ),
            ratingBar = { modifier, fl -> }
        )
    }
}
