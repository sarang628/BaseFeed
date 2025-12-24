package com.sarang.torang.compose.feed

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sarang.torang.compose.feed.internal.components.Comment
import com.sarang.torang.compose.feed.internal.components.CommentCount
import com.sarang.torang.compose.feed.internal.components.Contents
import com.sarang.torang.compose.feed.internal.components.Date
import com.sarang.torang.compose.feed.internal.components.ImagePager
import com.sarang.torang.compose.feed.internal.components.ProfileBottom
import com.sarang.torang.compose.feed.internal.components.ProfileTop
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.data.basefeed.FeedItemPageEvent
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.basefeed.Sample
import com.sarang.torang.data.basefeed.adjustHeight
import com.sarang.torang.data.basefeed.empty

private const val tag = "__Feed"
/** Feed 항목*/
@Composable
fun FeedItem(
    showLog             : Boolean                       = false,
    uiState             : FeedItemUiState               = FeedItemUiState.empty,
    ratingBarTintColor  : Color                         = Color(0xffe6cc00),
    favoriteColor       : Color                         = Color(0xffe6cc00),
    pageScroll          : Boolean                       = true,
    feedItemClickEvents : FeedItemClickEvents           = FeedItemClickEvents(tag = tag),
    onPage              : (FeedItemPageEvent) -> Unit   = { feedItemPageEvent -> Log.w(tag, "onPage callback isn't set page: ${feedItemPageEvent.page} isFirst: ${feedItemPageEvent.isFirst} isLast: ${feedItemPageEvent.isLast}") }
) {
    Column {
        Box(Modifier.fillMaxWidth()){
            ImagePager    (images                 = uiState.reviewImages,
                           onImage                = feedItemClickEvents.onImage,
                           height                 = uiState.adjustHeight,
                           userScrollEnabled      = pageScroll,
                           onPage                 = onPage)

            ProfileTop     (uiState               = uiState,
                            feedItemClickEvents   = feedItemClickEvents,
                            ratingBarTintColor    = ratingBarTintColor)

            ProfileBottom  (modifier              = Modifier.align(Alignment.BottomStart),
                            uiState               = uiState,
                            feedItemClickEvents   = feedItemClickEvents,
                            favoriteColor         = favoriteColor)
        }
        Contents        (userName   = uiState.userName,
                         contents   = uiState.contents,
                         onContents = feedItemClickEvents.onProfile)
        Comment         (comments   = uiState.comments)
        CommentCount    (count      = uiState.commentAmount,
                         onComment  = feedItemClickEvents.onComment)
        Date            (date       = uiState.createDate)
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFFDFDF6)
@Composable
fun PreviewFeed() {
    FeedItem(/* Preview */
        uiState = FeedItemUiState.Sample
    )
}