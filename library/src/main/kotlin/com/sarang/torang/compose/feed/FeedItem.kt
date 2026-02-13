package com.sarang.torang.compose.feed

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
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
import com.sarang.torang.compose.feed.internal.components.FeedBottom
import com.sarang.torang.compose.feed.internal.components.FeedTop
import com.sarang.torang.compose.feed.internal.components.ImagePager
import com.sarang.torang.compose.feed.internal.components.type.ExpandableTextType
import com.sarang.torang.compose.feed.internal.components.type.FeedImageLoader
import com.sarang.torang.compose.feed.internal.components.type.FeedImageLoaderData
import com.sarang.torang.compose.feed.internal.components.type.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.type.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.components.type.LocalVideoPlayerType
import com.sarang.torang.compose.feed.internal.components.type.VideoPlayerType
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.data.basefeed.FeedItemPageEvent
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.basefeed.Sample
import com.sarang.torang.data.basefeed.adjustHeight
import com.sarang.torang.data.basefeed.empty
import com.sarang.torang.data.basefeed.isVideo

private const val tag = "__Feed"
/** Feed 항목*/

@Composable
fun FeedItem(videoLoader         : VideoPlayerType,
             imageLoader         : FeedImageLoader,
             expandableText      : ExpandableTextType,
             uiState             : FeedItemUiState               = FeedItemUiState.empty,
             isPlaying           : Boolean                       = false,
             ratingBarTintColor  : Color                         = Color(0xffe6cc00),
             favoriteColor       : Color                         = Color(0xffe6cc00),
             pageScroll          : Boolean                       = true,
             feedItemClickEvents : FeedItemClickEvents           = remember { FeedItemClickEvents(tag = tag) },
             onPage              : (FeedItemPageEvent) -> Unit   = { feedItemPageEvent -> Log.w(tag, "onPage callback isn't set page: ${feedItemPageEvent.page} isFirst: ${feedItemPageEvent.isFirst} isLast: ${feedItemPageEvent.isLast}") }){
    CompositionLocalProvider(LocalVideoPlayerType provides videoLoader,
                                       LocalFeedImageLoader provides imageLoader,
                                       LocalExpandableTextType provides expandableText ) {
        FeedItem(uiState                = uiState,
                 isPlaying              = isPlaying,
                 ratingBarTintColor     = ratingBarTintColor,
                 favoriteColor          = favoriteColor,
                 pageScroll             = pageScroll,
                 feedItemClickEvents    = feedItemClickEvents,
                 onPage                 = onPage)
        }
}
@Composable
fun FeedItem(
    uiState             : FeedItemUiState               = FeedItemUiState.empty,
    isPlaying           : Boolean                       = false,
    ratingBarTintColor  : Color                         = Color(0xffe6cc00),
    favoriteColor       : Color                         = Color(0xffe6cc00),
    pageScroll          : Boolean                       = true,
    feedItemClickEvents : FeedItemClickEvents           = remember { FeedItemClickEvents(tag = tag) },
    onPage              : (FeedItemPageEvent) -> Unit   = { feedItemPageEvent -> Log.w(tag, "onPage callback isn't set page: ${feedItemPageEvent.page} isFirst: ${feedItemPageEvent.isFirst} isLast: ${feedItemPageEvent.isLast}")},
) {
    Column {
        Box(Modifier.fillMaxWidth()){
            ImagePager  (images                 = uiState.reviewImages,
                         onImage                = feedItemClickEvents.onImage,
                         height                 = uiState.adjustHeight,
                         userScrollEnabled      = pageScroll,
                         isPlaying              = isPlaying && uiState.isPlay,
                         onPage                 = onPage)

            FeedTop     (modifier               = Modifier,
                         profilePictureUrl      = uiState.profilePictureUrl,
                         rating                 = uiState.rating,
                         userName               = uiState.userName,
                         restaurantName         = uiState.restaurantName,
                         onName                 = feedItemClickEvents.onName,
                         onProfile              = feedItemClickEvents.onProfile,
                         onRestaurant           = feedItemClickEvents.onRestaurant,
                         onMenu                 = feedItemClickEvents.onMenu,
                         ratingBarTintColor     = ratingBarTintColor)

            FeedBottom  (modifier               = Modifier.align(Alignment.BottomStart)
                                                          .padding(vertical = 8.dp, horizontal = 12.dp),
                         isLike                 = uiState.isLike,
                         isLogin                = uiState.isLogin,
                         isFavorite             = uiState.isFavorite,
                         likeAmount             = uiState.likeAmount,
                         isVideo                = uiState.isVideo,
                         isVolumeOff            = uiState.isVolumeOff,
                         onLike                 = feedItemClickEvents.onLike,
                         onShare                = feedItemClickEvents.onShare,
                         onComment              = feedItemClickEvents.onComment,
                         onFavorite             = feedItemClickEvents.onFavorite,
                         favoriteColor          = favoriteColor,
                         onVolume               = feedItemClickEvents.onVolume)
        }
        Contents   (modifier   = Modifier.padding(start = 4.dp,
                                                  end = 4.dp,
                                                  top = 4.dp),
                    userName   = uiState.userName,
                    contents   = uiState.contents,
                    onContents = feedItemClickEvents.onProfile)
        Comment    (modifier = Modifier.padding(horizontal = 8.dp),
                    comments = uiState.comments)

        if(uiState.commentAmount > 0)
            CommentCount    (modifier   = Modifier.padding(horizontal = 4.dp),
                             count      = uiState.commentAmount,
                             onComment  = feedItemClickEvents.onComment)
        Date            (modifier = Modifier.padding(start = 4.dp,
                                                     end = 4.dp,
                                                     bottom = 4.dp),
                         date     = uiState.createDate)
    }
}

@Preview
@Composable
fun PreviewFeedItem(){
    PreviewFeed()
}