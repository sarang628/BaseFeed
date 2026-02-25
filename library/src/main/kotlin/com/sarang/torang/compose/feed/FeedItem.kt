package com.sarang.torang.compose.feed

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.sarang.torang.compose.component.Comment
import com.sarang.torang.compose.component.CommentIcon
import com.sarang.torang.compose.component.Contents
import com.sarang.torang.compose.component.Date
import com.sarang.torang.compose.component.Favorite
import com.sarang.torang.compose.component.FeedMediaPagerBox
import com.sarang.torang.compose.component.Like
import com.sarang.torang.compose.component.Menu
import com.sarang.torang.compose.component.ProfileImage
import com.sarang.torang.compose.component.RatingBar
import com.sarang.torang.compose.component.RestaurantName
import com.sarang.torang.compose.component.Share
import com.sarang.torang.compose.component.UserName
import com.sarang.torang.compose.component.type.ExpandableTextType
import com.sarang.torang.compose.component.type.FeedImageLoader
import com.sarang.torang.compose.component.type.LocalExpandableTextType
import com.sarang.torang.compose.component.type.LocalFeedImageLoader
import com.sarang.torang.compose.component.type.LocalVideoPlayerType
import com.sarang.torang.compose.component.type.VideoPlayerType
import com.sarang.torang.compose.component.util.nonEffectClickable
import com.sarang.torang.compose.feed.data.FeedBottomEvents
import com.sarang.torang.compose.feed.data.FeedItemClickEvents
import com.sarang.torang.compose.feed.data.FeedItemColors
import com.sarang.torang.compose.feed.data.FeedItemPageEvent
import com.sarang.torang.compose.feed.data.FeedTopEvents
import com.sarang.torang.compose.feed.data.empty

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
             colors              : FeedItemColors = FeedItemColors(),
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
             colors              : FeedItemColors = FeedItemColors(),
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

@Composable
fun FeedTop(modifier            : Modifier         = Modifier,
            uiState             : FeedTopUiState   = FeedTopUiState(),
            events              : FeedTopEvents    = FeedTopEvents(),
            ratingBarTintColor  : Color            = MaterialTheme.colorScheme.primary){
    ConstraintLayout(modifier = modifier.fillMaxWidth()
        .nonEffectClickable(),
        constraintSet = feedTopConstraintSet()) {
        ProfileImage(
            modifier = Modifier.testTag("imgProfile")
                .layoutId("imgProfile"),
            url = uiState.profilePictureUrl,
            onProfile = events.onProfile
        )

        Row(modifier = Modifier.layoutId("userName")) {
            Column(verticalArrangement  = Arrangement.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    UserName(
                        modifier = Modifier.testTag("txtUserName"),
                        userName = uiState.userName,
                        onName = events.onName
                    )

                    Spacer    (modifier          = Modifier.width(4.dp))

                    RatingBar (modifier          = Modifier.testTag("rbProfile"),
                        rating            = uiState.rating,
                        progressTintColor = ratingBarTintColor)
                }
                RestaurantName(
                    modifier = Modifier.testTag("txtRestaurantName"),
                    restaurantName = uiState.restaurantName,
                    onRestaurant = events.onRestaurant
                )
            }
        }

        Menu(
            modifier = Modifier.layoutId("btnMenu")
                .testTag("btnMenu"),
            onMenu = events.onMenu
        )
    }

}

private fun feedTopConstraintSet() : ConstraintSet{
    return ConstraintSet {
        val imgProfile = createRefFor("imgProfile")
        val containerUserAndRestaurantName = createRefFor("userName")
        val btnMenu = createRefFor("btnMenu")

        constrain(imgProfile){
            start.linkTo(parent.start, margin = 4.dp)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        constrain(containerUserAndRestaurantName){
            start.linkTo(imgProfile.end, margin = 2.dp)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        constrain(btnMenu){
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x111111)
@Composable
fun PreviewFeedTop(){
    var userName : String by remember { mutableStateOf("userName userName userName userName userName userName userName userName userName userName userName ") }
    var rating : String by remember { mutableStateOf("4.0") }
    var restaurantName : String by remember { mutableStateOf("restaurantName restaurantName restaurantName restaurantName restaurantName") }

    Column {
        FeedTop(uiState = FeedTopUiState(
            userName = userName,
            rating = try {
                rating.toFloat()
            } catch (e: Exception) {
                0f
            },
            restaurantName = restaurantName
        )
        )
    }
}

@Composable
fun FeedBottom(modifier              : Modifier             = Modifier,
               uiState               : FeedBottomUiState    = FeedBottomUiState(),
               events                : FeedBottomEvents     = remember { FeedBottomEvents() },
               isVideo               : Boolean              = false,
               favoriteColor         : Color                = MaterialTheme.colorScheme.primary, ){
    Column(modifier = modifier.fillMaxWidth()
        .nonEffectClickable()){
        if(isVideo){
            Box(Modifier.fillMaxWidth()){
                Volume(modifier = Modifier.align(Alignment.CenterEnd),
                    isMute   = uiState.isVolumeOff,
                    onVolume = events.onVolume)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Box(Modifier.fillMaxWidth()){
            Row(modifier            = Modifier.align(Alignment.CenterStart),
                verticalAlignment   = Alignment.CenterVertically) {
                Like(
                    modifier = Modifier.testTag("btnLike"),
                    likeAmount = uiState.likeAmount,
                    isLike = uiState.isLike,
                    animation = uiState.isLogin,
                    onLike = events.onLike
                )
                Spacer(modifier = Modifier.width(12.dp))
                CommentIcon(
                    modifier = Modifier.testTag("btnComment"),
                    onComment = events.onComment
                )
                Spacer(modifier = Modifier.width(12.dp))
                Share(
                    modifier = Modifier.testTag("btnShare"),
                    onShare = events.onShare
                )
            }

            Favorite(
                modifier = Modifier.align(Alignment.CenterEnd),
                isFavorite = uiState.isFavorite,
                onFavorite = events.onFavorite,
                color = favoriteColor
            )
        }
    }
}

@Composable
fun Volume(modifier : Modifier      = Modifier,
           isMute   : Boolean       = false,
           onVolume : () -> Unit    = {}){
    Icon(modifier           = modifier.clickable(enabled = true,
        onClick = onVolume),
        imageVector        = if(isMute) Icons.AutoMirrored.Default.VolumeOff
        else Icons.AutoMirrored.Default.VolumeUp,
        contentDescription = null,
        tint               = Color.White)
}

@Preview(showBackground = true, backgroundColor = 0x111111)
@Composable
fun PreviewFeedBottom(){
    FeedBottom(
        uiState = FeedBottomUiState(isVolumeOff = true),
        isVideo = true
    )
}