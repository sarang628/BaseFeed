package com.sarang.torang.compose.feed

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.components.Comment
import com.sarang.torang.compose.feed.internal.components.Contents
import com.sarang.torang.compose.feed.internal.components.Date
import com.sarang.torang.compose.feed.internal.components.FeedBottom
import com.sarang.torang.compose.feed.internal.components.FeedItemColors
import com.sarang.torang.compose.feed.internal.components.FeedMediaPagerBox
import com.sarang.torang.compose.feed.internal.components.FeedTop
import com.sarang.torang.compose.feed.internal.components.type.ExpandableTextType
import com.sarang.torang.compose.feed.internal.components.type.FeedImageLoader
import com.sarang.torang.compose.feed.internal.components.type.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.type.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.components.type.LocalVideoPlayerType
import com.sarang.torang.compose.feed.internal.components.type.VideoPlayerType
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.data.basefeed.FeedItemPageEvent
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.basefeed.adjustHeight
import com.sarang.torang.data.basefeed.empty
import com.sarang.torang.data.basefeed.isVideo

private const val tag = "__FeedItem"

/**
 * 피드 항목
 *
 * @param uiState            Feed 항목의 UI 상태
 * @param events             Feed element 클릭 이벤트
 * @param isPlaying          비디오 플레이 여부
 * @param colors             피드 요소 색상 설정
 * @param userScrollEnabled  pager 스와이프 스크롤 허용 여부
 * @param videoLoader        video player 는 외부에서 구현
 * @param imageLoader        image loader 는 외부에서 구현
 * @param expandableText     클릭 확장 텍스트는 외부에서 구현
 * @param onPage             페이지 변경 이벤트
 */
@Composable
fun FeedItem(uiState             : FeedItemUiState              = FeedItemUiState.empty,
             events              : FeedItemClickEvents          = remember { FeedItemClickEvents(tag = tag) },
             isPlaying           : Boolean                      = false,
             colors              : FeedItemColors               = FeedItemColors(),
             userScrollEnabled   : Boolean                      = true,
             videoLoader         : VideoPlayerType              = {},
             imageLoader         : FeedImageLoader              = {},
             expandableText      : ExpandableTextType           = {},
             onPage              : (FeedItemPageEvent) -> Unit  = { feedItemPageEvent -> Log.w(tag, "onPage callback isn't set page: ${feedItemPageEvent.page} isFirst: ${feedItemPageEvent.isFirst} isLast: ${feedItemPageEvent.isLast}") }){
    CompositionLocalProvider(LocalVideoPlayerType provides videoLoader,
                                       LocalFeedImageLoader provides imageLoader,
                                       LocalExpandableTextType provides expandableText ) {
        FeedItem(uiState                = uiState,
                 isPlaying              = isPlaying,
                 colors                 = colors,
                 userScrollEnabled      = userScrollEnabled,
                 events                 = events,
                 onPage                 = onPage)
        }
}

/**
 * 피드 항목
 *
 * @param uiState            Feed 항목의 UI 상태
 * @param events             Feed element 클릭 이벤트
 * @param isPlaying          비디오 플레이 여부
 * @param colors             피드 요소 색상 설정
 * @param userScrollEnabled  pager 스와이프 스크롤 허용 여부
 * @param onPage             페이지 변경 이벤트
 */
@Composable
fun FeedItem(uiState             : FeedItemUiState               = FeedItemUiState.empty,
             events              : FeedItemClickEvents           = remember { FeedItemClickEvents(tag = tag) },
             isPlaying           : Boolean                       = false,
             colors              : FeedItemColors                = FeedItemColors(),
             userScrollEnabled   : Boolean                       = true,
             onPage              : (FeedItemPageEvent) -> Unit   = { feedItemPageEvent -> Log.w(tag, "onPage callback isn't set page: ${feedItemPageEvent.page} isFirst: ${feedItemPageEvent.isFirst} isLast: ${feedItemPageEvent.isLast}")},
) {
    Column {
        FeedMediaPagerBox (images                 = uiState.reviewImages,
                           onImage                = events.onImage,
                           height                 = uiState.adjustHeight,
                           userScrollEnabled      = userScrollEnabled,
                           isPlaying              = isPlaying && uiState.isPlay,
                           onPage                 = onPage) {
            FeedTop (modifier               = Modifier,
                     uiState                = uiState.feedTopUiState,
                     events                 = events.feedTopEvents,
                     ratingBarTintColor     = colors.ratingBarColor)

            FeedBottom (modifier       = Modifier.align(Alignment.BottomStart)
                                                 .padding(vertical = 8.dp, horizontal = 12.dp),
                        uiState        = uiState.feedBottomUiState,
                        events         = events.feedBottomEvents,
                        isVideo        = uiState.isVideo,
                        favoriteColor  = colors.favoriteColor)
        }
        Column(Modifier.padding(all = 4.dp)) {
            Contents (userName   = uiState.feedTopUiState.userName,
                      contents   = uiState.contents,
                      onContents = events.feedTopEvents.onProfile)

            Comment (commentCount    = uiState.commentAmount,
                     comments        = uiState.comments,
                     onComment       = events.feedBottomEvents.onComment)

            Date (date     = uiState.createDate)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedItem(){
    PreviewFeed()
}