package com.sryang.base.feed.compose.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.base.feed.data.Review
import com.sryang.base.feed.data.testReviewData

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
    imageServerUrl: String,                 // 이미지 서버
    profileImageServerUrl: String,          // 프로필 서버
    ratingBar: @Composable (Float) -> Unit  // 평점 바
) {
    Column {
        FeedTop(
            name = review.user.name,
            onProfile = onProfile,
            onMenu = onMenu,
            onName = onName,
            onRestaurant = onRestaurant,
            profileImageServerUrl = profileImageServerUrl,
            ratingBar = ratingBar,
            profilePictureUrl = review.user.profilePictureUrl,
            rating = review.rating,
            restaurantName = review.restaurant.restaurantName
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (review.reviewImages.isNotEmpty()) {
            FeedMid(
                review.reviewImages,
                onImage = onImage,
                progressSize = 30.dp,
                errorIconSize = 30.dp,
                imageServerUrl = imageServerUrl
            )
        }
        FeedBottom(
            onLike = onLike,
            onComment = onComment,
            onShare = onShare,
            onFavorite = onFavorite,
            isLike = review.isLike,
            isFavorite = review.isFavorite,
            contents = review.contents,
        )
    }
}


@Preview
@Composable
fun PreViewItemFeed() {
    Feed(
        onLike = {},
        onComment = {},
        onShare = {},
        onFavorite = {},
        onMenu = { /*TODO*/ },
        onName = { /*TODO*/ },
        onRestaurant = {},
        onImage = {},
        profileImageServerUrl = "http://sarang628.iptime.org:89/profile_images/",
        imageServerUrl = "http://sarang628.iptime.org:89/review_images/",
        onProfile = {},
        review = testReviewData()
    ) {

    }
}