package com.sarang.base_feed.ui.itemfeed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.base_feed.uistate.FeedUiState
import com.sarang.base_feed.uistate.getFeedsByFile

@Composable
fun ItemFeed(
    uiState: FeedUiState,
    onProfile: (() -> Unit)? = null,
    onLike: ((Int) -> Unit)? = null,
    onComment: ((Int) -> Unit)? = null,
    onShare: ((Int) -> Unit)? = null,
    onFavorite: ((Int) -> Unit)? = null,
    onMenu: (() -> Unit)? = null,
    onName: (() -> Unit)? = null,
    onRestaurant: (() -> Unit)? = null,
    onImage: ((Int) -> Unit)? = null,
    imageServerUrl: String = "",
    profileImageServerUrl: String = ""
) {
    val scope = rememberCoroutineScope()
    Column {
        ItemFeedTop(
            uiState = uiState.itemFeedTopUiState,
            onProfile = onProfile,
            onMenu = onMenu,
            onName = onName,
            onRestaurant = onRestaurant,
            profileImageServerUrl = profileImageServerUrl
        )
        Spacer(modifier = Modifier.height(4.dp))
        ItemFeedMid(
            uiState.reviewImages,
            onImage = onImage,
            progressSize = 30.dp,
            errorIconSize = 30.dp,
            imageServerUrl = imageServerUrl
        )
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
    val list = getFeedsByFile(LocalContext.current)
    ItemFeed(uiState = list[0].FeedUiState())
}
