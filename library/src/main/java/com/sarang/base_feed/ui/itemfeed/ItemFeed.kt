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

@Composable
fun ItemFeed(
    uiState: FeedUiState,
    onProfile: ((Int) -> Unit)? = null,
    onLike: ((Int) -> Unit),
    onComment: ((Int) -> Unit),
    onShare: ((Int) -> Unit),
    onFavorite: ((Int) -> Unit),
    onMenu: (() -> Unit),
    onName: (() -> Unit),
    onRestaurant: ((Int) -> Unit),
    onImage: ((Int) -> Unit),
    imageServerUrl: String = "",
    profileImageServerUrl: String = "",
    ratingBar: @Composable (Float) -> Unit
) {
    val scope = rememberCoroutineScope()
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
    //val list = getFeedsByFile(LocalContext.current)
    //ItemFeed(uiState = list[0].FeedUiState())
}
