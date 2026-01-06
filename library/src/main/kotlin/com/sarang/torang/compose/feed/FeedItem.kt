package com.sarang.torang.compose.feed

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.components.Comment
import com.sarang.torang.compose.feed.internal.components.CommentCount
import com.sarang.torang.compose.feed.internal.components.Contents
import com.sarang.torang.compose.feed.internal.components.Date
import com.sarang.torang.compose.feed.internal.components.ImagePager
import com.sarang.torang.compose.feed.internal.components.FeedBottom
import com.sarang.torang.compose.feed.internal.components.FeedTop
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.data.basefeed.FeedItemPageEvent
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.basefeed.Sample
import com.sarang.torang.data.basefeed.adjustHeight
import com.sarang.torang.data.basefeed.empty
import kotlinx.coroutines.flow.MutableStateFlow

private const val tag = "__Feed"
/** Feed 항목*/
@Composable
fun FeedItem(
    uiState             : FeedItemUiState               = FeedItemUiState.empty,
    ratingBarTintColor  : Color                         = Color(0xffe6cc00),
    favoriteColor       : Color                         = Color(0xffe6cc00),
    pageScroll          : Boolean                       = true,
    feedItemClickEvents : FeedItemClickEvents           = FeedItemClickEvents(tag = tag),
    onPage              : (FeedItemPageEvent) -> Unit   = { feedItemPageEvent -> Log.w(tag, "onPage callback isn't set page: ${feedItemPageEvent.page} isFirst: ${feedItemPageEvent.isFirst} isLast: ${feedItemPageEvent.isLast}") }
) {
    Column {
        Box(Modifier.fillMaxWidth()){
            ImagePager  (images                 = uiState.reviewImages,
                         onImage                = feedItemClickEvents.onImage,
                         height                 = uiState.adjustHeight,
                         userScrollEnabled      = pageScroll,
                         onPage                 = onPage)

            FeedTop     (modifier               = Modifier.padding(start = 18.dp, top = 1.dp),
                         profilePictureUrl      = uiState.profilePictureUrl,
                         rating                 = uiState.rating,
                         userName               = uiState.userName,
                         restaurantName         = uiState.restaurantName,
                         onName                 = feedItemClickEvents.onName,
                         onProfile              = feedItemClickEvents.onProfile,
                         onRestaurant           = feedItemClickEvents.onRestaurant,
                         onMenu                 = feedItemClickEvents.onMenu,
                         ratingBarTintColor     = ratingBarTintColor)

            FeedBottom  (modifier               = Modifier
                .align(Alignment.BottomStart)
                .padding(vertical = 8.dp, horizontal = 12.dp),
                         isLike                 = uiState.isLike,
                         isLogin                = uiState.isLogin,
                         isFavorite             = uiState.isFavorite,
                         likeAmount             = uiState.likeAmount,
                         feedItemClickEvents    = feedItemClickEvents,
                         favoriteColor          = favoriteColor)
        }
        Contents   (modifier   = Modifier.padding(start = 4.dp,
                                                  end = 4.dp,
                                                  top = 4.dp),
                    userName   = uiState.userName,
                    contents   = uiState.contents,
                    onContents = feedItemClickEvents.onProfile)
        Comment    (modifier = Modifier.padding(horizontal = 8.dp),
                    comments   = uiState.comments)

        if(uiState.commentAmount > 0)
            CommentCount    (modifier   = Modifier.padding(horizontal = 8.dp),
                             count      = uiState.commentAmount,
                             onComment  = feedItemClickEvents.onComment)
        Date            (modifier = Modifier.padding(start = 8.dp,
                                                     end = 8.dp,
                                                     bottom = 4.dp),
                         date     = uiState.createDate)
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFFDFDF6)
@Composable
fun PreviewFeed() {
    var isLike by remember { mutableStateOf(false) }
    FeedItem(/* Preview */
        uiState = FeedItemUiState.Sample.copy(isLike = isLike, isLogin = true),
        feedItemClickEvents = FeedItemClickEvents(
            onLike = { isLike = !isLike }
        )
    )
}