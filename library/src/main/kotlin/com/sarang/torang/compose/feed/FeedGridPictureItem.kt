package com.sarang.torang.compose.feed

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.sarang.torang.compose.component.Comment
import com.sarang.torang.compose.component.Contents
import com.sarang.torang.compose.component.Date
import com.sarang.torang.compose.component.FeedMedia
import com.sarang.torang.compose.component.Menu
import com.sarang.torang.compose.component.ProfileImage
import com.sarang.torang.compose.component.RatingBar
import com.sarang.torang.compose.component.UserName
import com.sarang.torang.compose.component.type.ExpandableTextType
import com.sarang.torang.compose.component.type.FeedImageLoader
import com.sarang.torang.compose.component.type.LocalExpandableTextType
import com.sarang.torang.compose.component.type.LocalFeedImageLoader
import com.sarang.torang.compose.component.type.LocalVideoPlayerType
import com.sarang.torang.compose.component.type.VideoPlayerType
import com.sarang.torang.compose.component.util.nonEffectClickable
import com.sarang.torang.compose.feed.data.FeedItemClickEvents
import com.sarang.torang.compose.feed.data.FeedItemColors
import com.sarang.torang.compose.feed.data.FeedItemPageEvent
import com.sarang.torang.compose.feed.data.FeedTopEvents
import com.sarang.torang.compose.feed.data.empty

private const val tag = "__FeedGridPictureItem"

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
fun FeedGridPictureItem(uiState             : FeedItemUiState = FeedItemUiState.Companion.empty,
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
fun FeedGridPictureItem(uiState             : FeedItemUiState = FeedItemUiState.Companion.empty,
                        events              : FeedItemClickEvents           = remember { FeedItemClickEvents(tag = tag) },
                        isPlaying           : Boolean                       = false,
                        colors              : FeedItemColors = FeedItemColors(),
                        userScrollEnabled   : Boolean                       = true,
                        onPage              : (FeedItemPageEvent) -> Unit   = { feedItemPageEvent -> Log.w(tag, "onPage callback isn't set page: ${feedItemPageEvent.page} isFirst: ${feedItemPageEvent.isFirst} isLast: ${feedItemPageEvent.isLast}")},
) {
    Column {
        FeedGridPictureTop (modifier               = Modifier,
                            uiState                = uiState.feedTopUiState,
                            events                 = events.feedTopEvents,
                            ratingBarTintColor     = colors.ratingBarColor)

        Column(Modifier.padding(start = 6.dp, end = 6.dp, bottom = 4.dp)) {
            Contents(
                userName = uiState.feedTopUiState.userName,
                contents = uiState.contents,
                onContents = events.feedTopEvents.onProfile
            )
        }

        Spacer(Modifier.height(4.dp))

            GridReviewImage(url = uiState.reviewImages,
                            isPlaying = isPlaying)

            FeedBottom (modifier       = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                        uiState        = uiState.feedBottomUiState,
                        events         = events.feedBottomEvents,
                        isVideo        = uiState.isVideo,
                        favoriteColor  = colors.favoriteColor,
                        iconTint       = Color.Black)
        Column(Modifier.padding(all = 4.dp)) {
            Comment (commentCount    = uiState.commentAmount,
                     comments        = uiState.comments,
                     onComment       = events.feedBottomEvents.onComment)

            Date (date     = uiState.createDate)
        }
    }
}


@Composable
fun FeedGridPictureTop(modifier            : Modifier         = Modifier,
                       uiState             : FeedTopUiState   = FeedTopUiState(),
                       events              : FeedTopEvents    = FeedTopEvents(),
                       ratingBarTintColor  : Color            = MaterialTheme.colorScheme.primary){
    Column {
            ConstraintLayout(modifier = modifier
                .fillMaxWidth()
                .nonEffectClickable(),
                constraintSet = feedTopConstraintSet()) {
                ProfileImage(
                    modifier = Modifier
                        .testTag("imgProfile")
                        .layoutId("imgProfile"),
                    url = uiState.profilePictureUrl,
                    onProfile = events.onProfile
                )

                Row(modifier = Modifier.layoutId("userName")) {
                    Column(verticalArrangement = Arrangement.Center) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            UserName(
                                modifier = Modifier.testTag("txtUserName"),
                                userName = uiState.userName,
                                onName = events.onName,
                                color = Color.Black
                            )
                        }
                        RatingBar(
                            modifier = Modifier
                                .testTag("rbRating")
                                .layoutId("rbRating"),
                            rating = uiState.rating,
                            progressTintColor = ratingBarTintColor
                        )
                    }
                }

                Menu(
                    modifier = Modifier
                        .layoutId("btnMenu")
                        .testTag("btnMenu"),
                    onMenu = events.onMenu
                )
            }
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

@Preview(showBackground = true)
@Composable
fun PreviewFeedGridPictureTop(){
    var userName : String by remember { mutableStateOf("userName userName userName userName userName userName userName userName userName userName userName ") }
    var rating : String by remember { mutableStateOf("4.0") }
    var restaurantName : String by remember { mutableStateOf("restaurantName restaurantName restaurantName restaurantName restaurantName") }

    Column {
        FeedGridPictureTop(uiState = FeedTopUiState(
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
fun GridReviewImage(
    url           : List<String>  = emptyList(),
    isPlaying     : Boolean       = false,
    onImage       : () -> Unit    = {}
){


    Column(Modifier
        .padding(horizontal = 8.dp)
        .height(250.dp)
        .clip(RoundedCornerShape(12.dp))
    ) {

        val row = url.take(4).chunked(2)

        var colCount = 0
        row.forEach { images ->
            var rowCount = 0
            if (colCount == 1) Spacer(Modifier.height(8.dp))
            Row(Modifier.weight(1f)) {
                images.forEach { image ->
                    if(rowCount == 1) Spacer(Modifier.width(8.dp))
                    Box(Modifier.fillMaxSize()
                                .weight(1f)){
                        FeedMedia(url = image,
                                  isPlaying = isPlaying,
                                  onImage = onImage)

                        if(url.size > 4 && rowCount == 1 && colCount == 1){
                            Box(modifier = Modifier.fillMaxSize()
                                                   .background(Color(0x55000000))){
                                Text(modifier = Modifier.align(Alignment.Center),
                                    text = "+${(url.size-4)}",
                                    fontSize = 30.sp,
                                    color = Color.White,
                                    style = TextStyle.Default.copy(textAlign = TextAlign.Center))
                            }
                        }
                    }
                    rowCount++
                }
            }
            colCount++
        }
    }
}


@Preview
@Composable
fun PreviewGridReviewImage(){
    GridReviewImage(url = listOf("a.m3u8"))
}


@Preview(showBackground = true)
@Composable
fun PreviewFeedGridPictureItem(){
    FeedGridPictureItem()
}