package com.sarang.base_feed.ui.itemfeed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.base_feed.uistate.FeedUiState
import com.sarang.base_feed.uistate.testFeedUiState

@Composable
fun ItemFeed(
    uiState: FeedUiState,                   // 피드 상단,중앙,하단을 합친 ui 상태 값
    onProfile: () -> Unit,                  // 프로필 클릭
    onLike: () -> Unit,                // 좋아요 클릭
    onComment: () -> Unit,             // 코멘트 클릭
    onShare: () -> Unit,               // 공유 클릭
    onFavorite: () -> Unit,            // 즐겨찾기 클릭
    onMenu: (() -> Unit),                   // 메뉴 클릭
    onName: (() -> Unit),                   // 이름 클릭
    onRestaurant: (() -> Unit),             // 음식점 클릭
    onImage: (Int) -> Unit,               // 이미지 클릭
    imageServerUrl: String,                 // 이미지 서버
    profileImageServerUrl: String,          // 프로필 서버
    ratingBar: @Composable (Float) -> Unit  // 평점 바
) {
    Column {
        ItemFeedTop(
            uiState = uiState.itemFeedTopUiState,
            onProfile = onProfile,
            onMenu = onMenu,
            onName = onName,
            onRestaurant = onRestaurant,
            profileImageServerUrl = profileImageServerUrl,
            ratingBar = ratingBar
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (uiState.reviewImages.isNotEmpty()) {
            ItemFeedMid(
                uiState.reviewImages,
                onImage = onImage,
                progressSize = 30.dp,
                errorIconSize = 30.dp,
                imageServerUrl = imageServerUrl
            )
        }
        FeedBottom(
            uiState = uiState.itemFeedBottomUiState,
            onLike = onLike,
            onComment = onComment,
            onShare = onShare,
            onFavorite = onFavorite
        )
    }
}


@Preview
@Composable
fun PreViewItemFeed() {
    ItemFeed(
        uiState = testFeedUiState(),
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
        onProfile = {}
    ) {

    }
}
