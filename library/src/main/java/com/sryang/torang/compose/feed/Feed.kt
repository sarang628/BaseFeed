package com.sryang.torang.compose.feed

import TorangAsyncImage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.sryang.torang.compose.feed.internal.components.Comment
import com.sryang.torang.compose.feed.internal.components.CommentImage
import com.sryang.torang.compose.feed.internal.components.ExpandableText
import com.sryang.torang.compose.feed.internal.components.FavoriteImage
import com.sryang.torang.compose.feed.internal.components.ImagePagerWithIndicator
import com.sryang.torang.compose.feed.internal.components.LikeImage
import com.sryang.torang.compose.feed.internal.components.ShareImage
import com.sryang.torang.compose.feed.internal.util.clickable1
import com.sryang.torang.data.basefeed.Review
import com.sryang.torang.data.basefeed.testReviewData

@Composable
fun Feed(
    review: Review,                         // 피드 상단,중앙,하단을 합친 ui 상태 값
    ratingBar: @Composable (Modifier, Float) -> Unit,  // 평점 바
    isZooming: ((Boolean) -> Unit)? = null
) {
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
                    .size(40.dp)
                    .clickable1(review.onProfile::invoke)
                    .clip(RoundedCornerShape(20.dp)),
                model = review.user.profilePictureUrl,
                progressSize = 20.dp,
                errorIconSize = 20.dp
            )
            // 사용자명
            Text(
                modifier = Modifier
                    .layoutId("refName")
                    .clickable1(review.onName::invoke),
                text = review.user.name,
            )
            // 평점
            ratingBar.invoke(Modifier.layoutId("refRatingBar"), review.rating)
            // 음식점명
            Text(
                modifier = Modifier
                    .layoutId("refRestaurantName")
                    .clickable1(review.onRestaurant::invoke),
                text = review.restaurant.restaurantName
            )
            // 메뉴
            IconButton(
                modifier = Modifier.layoutId("refMenu"),
                onClick = review.onMenu::invoke
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
            // 이미지 페이저
            if (review.reviewImages.isNotEmpty()) {
                ImagePagerWithIndicator(
                    modifier = Modifier.layoutId("reviewImages"),
                    images = review.reviewImages,
                    onImage = review.onImage,
                    isZooming = isZooming
                )
            }
            // 좋아요 아이콘
            LikeImage(
                modifier = Modifier.layoutId("heart"),
                isLike = review.isLike,
                onLike = review.onLike
            )
            // 코멘트 아이콘
            CommentImage(modifier = Modifier.layoutId("comment"), onComment = review.onComment)
            // 공유 아이콘
            ShareImage(modifier = Modifier.layoutId("share"), onShare = review.onShare)
            // 즐겨찾기 아이콘
            FavoriteImage(
                modifier = Modifier.layoutId("favorite"),
                isFavorite = review.isFavorite,
                onFavorite = review.onFavorite
            )
            // 리뷰 내용
            if (review.contents.isNotEmpty()) {
                ExpandableText(modifier = Modifier.layoutId("contents"), text = review.contents)
            }

            // 좋아요 갯수
            if (review.likeAmount > 0) {
                Text(
                    modifier = Modifier.layoutId("likeCount"),
                    text = "좋아요 ${review.likeAmount} 개",
                    color = Color.DarkGray
                )
            }

            // 코멘트
            Comment(Modifier.layoutId("comments"), review.comments)

            // 코멘트 갯수
            if (review.commentAmount > 0) {
                Text(
                    modifier = Modifier.layoutId("commentCount"),
                    text = "댓글 ${review.commentAmount} 개 모두보기",
                    color = Color.DarkGray
                )
            }
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

        constrain(refProfile) {
            top.linkTo(parent.top)
            start.linkTo(parent.start, margin = 12.dp)
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
            top.linkTo(reviewImages.bottom)
            start.linkTo(parent.start, margin = 8.dp)
        }

        constrain(comment) {
            top.linkTo(reviewImages.bottom)
            start.linkTo(heart.end, margin = 8.dp)
        }

        constrain(share) {
            top.linkTo(reviewImages.bottom)
            start.linkTo(comment.end, margin = 8.dp)
        }

        constrain(favorite) {
            top.linkTo(reviewImages.bottom)
            end.linkTo(parent.end, margin = 8.dp)
        }

        constrain(contents) {
            top.linkTo(heart.bottom, margin = 8.dp)
            start.linkTo(heart.start)
            end.linkTo(favorite.end)
            width = Dimension.fillToConstraints
        }
        constrain(likeCount) {
            top.linkTo(contents.bottom)
        }
        constrain(comments) {
            top.linkTo(likeCount.bottom)
        }
        constrain(commentCount) {
            top.linkTo(comments.bottom)
        }
    }
}


@Preview
@Composable
fun PreViewItemFeed() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Feed(
            review = testReviewData(),
            ratingBar = { modifier, fl -> }
        )
    }
}