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
    onProfile: () -> Unit,                  // 프로필 클릭
    onLike: () -> Unit,                     // 좋아요 클릭
    onComment: () -> Unit,                  // 코멘트 클릭
    onShare: () -> Unit,                    // 공유 클릭
    onFavorite: () -> Unit,                 // 즐겨찾기 클릭
    onMenu: (() -> Unit),                   // 메뉴 클릭
    onName: (() -> Unit),                   // 이름 클릭
    onRestaurant: (() -> Unit),             // 음식점 클릭
    onImage: (Int) -> Unit,                 // 이미지 클릭
    ratingBar: @Composable (Modifier, Float) -> Unit,  // 평점 바
    isZooming: ((Boolean) -> Unit)? = null
) {
    Column {
        ConstraintLayout(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            constraintSet = feedCommentsConstraint()

        ) {
            // 프로필 이미지
            TorangAsyncImage(
                modifier = Modifier
                    .layoutId("refProfile")
                    .size(40.dp)
                    .clickable1(onProfile::invoke)
                    .clip(RoundedCornerShape(20.dp)),
                model = review.user.profilePictureUrl,
                progressSize = 20.dp,
                errorIconSize = 20.dp
            )
            // 사용자명
            Text(
                modifier = Modifier
                    .layoutId("refName")
                    .clickable1(onName::invoke),
                text = review.user.name,
            )
            // 평점
            ratingBar.invoke(Modifier.layoutId("refRatingBar"), review.rating)
            // 음식점명
            Text(
                modifier = Modifier
                    .layoutId("refRestaurantName")
                    .clickable1(onRestaurant::invoke),
                text = review.restaurant.restaurantName
            )
            // 메뉴
            IconButton(
                modifier = Modifier.layoutId("refMenu"),
                onClick = onMenu::invoke
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }

            if (review.reviewImages.isNotEmpty()) {
                ImagePagerWithIndicator(
                    modifier = Modifier.layoutId("reviewImages"),
                    images = review.reviewImages,
                    onImage = onImage,
                    isZooming = isZooming
                )
            }
            LikeImage(modifier = Modifier.layoutId("heart"), isLike = review.isLike, onLike = onLike)
            CommentImage(modifier = Modifier.layoutId("comment"), onComment = onComment)
            ShareImage(modifier = Modifier.layoutId("share"), onShare = onShare)
            FavoriteImage(
                modifier = Modifier.layoutId("favorite"),
                isFavorite = review.isFavorite,
                onFavorite = onFavorite
            )

            if (review.contents.isNotEmpty()) {
                ExpandableText(modifier = Modifier.layoutId("contents"), text = review.contents)
            }

            if (review.likeAmount != null && review.likeAmount > 0) {
                Text(
                    modifier = Modifier.layoutId("likeCount"),
                    text = "좋아요 ${review.likeAmount} 개",
                    color = Color.DarkGray
                )
            }

            Comment(Modifier.layoutId("comments"), review.comments)

            if (review.commentAmount != null && review.commentAmount > 0) {
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

        constrain(reviewImages){
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
            start.linkTo(parent.start)
            end.linkTo(parent.end)
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
            onLike = {},
            onComment = {},
            onShare = {},
            onFavorite = {},
            onMenu = { /*TODO*/ },
            onName = { /*TODO*/ },
            onRestaurant = {},
            onImage = {},
            onProfile = {},
            review = testReviewData(),
            ratingBar = { modifier, fl -> }
        )
    }
}
