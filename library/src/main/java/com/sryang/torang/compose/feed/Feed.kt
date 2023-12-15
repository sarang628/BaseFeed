package com.sryang.torang.compose.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    ratingBar: @Composable (Float) -> Unit  // 평점 바
) {
    Column {
        FeedTop(
            name = review.user.name,
            onProfile = onProfile,
            onMenu = onMenu,
            onName = onName,
            onRestaurant = onRestaurant,
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
            ratingBar = {}
        )
    }
}
