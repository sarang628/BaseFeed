package com.sarang.torang.compose.feed.internal.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.components.type.FeedImageLoaderData
import com.sarang.torang.compose.feed.internal.components.type.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.components.type.LocalVideoPlayerType
import com.sarang.torang.compose.feed.internal.util.isVideoType
import com.sarang.torang.compose.feed.internal.util.nonEffectClickable
import com.sarang.torang.data.basefeed.FeedItemPageEvent

private const val tag = "__ImagePagerWithIndicator"
/**
 * @param images 이미지 리스트
 * @param onImage 이미지 클릭 이벤트
 * @param showIndicator indicator 표시 여부
 */
@Composable
fun ImagePager(
    modifier                : Modifier          = Modifier,
    images                  : List<String>      = listOf(),
    onImage                 : (Int) -> Unit     = { Log.i(tag, "onImage callback is not set page : $it") },
    showIndicator           : Boolean           = true,
    height                  : Dp                = 300.dp,
    userScrollEnabled       : Boolean           = true,
    indicatorBottomPadding  : Dp                = 12.dp,
    onPage                  : (FeedItemPageEvent) -> Unit    = { feedItemPageEvent -> Log.w(tag, "onPage callback is not set page: ${feedItemPageEvent.page} isFirst: ${feedItemPageEvent.isFirst} isLast: ${feedItemPageEvent.isLast}") }
) {
    val pagerState: PagerState = rememberPagerState { images.size }

    SetOnPageListener(pagerState = pagerState,
                      imageSize  = images.size,
                      onPage     = onPage)
    Box {
        HorizontalPager(modifier            = Modifier.height(height),
                        state               = pagerState,
                        userScrollEnabled   = userScrollEnabled) { page ->
            if(images[page].isVideoType){
                LocalVideoPlayerType.current(images[page])
            }
            else {
                LocalFeedImageLoader.current(
                    FeedImageLoaderData(
                        url = images[page],
                        contentScale = ContentScale.Crop,
                        height = height,
                        modifier = modifier
                            .testTag("imgReview")
                            .fillMaxSize()
                            .nonEffectClickable(onClick = { onImage.invoke(page); }),
                    )
                )
            }
        }
        if (showIndicator)
            PagerIndicator(modifier   = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = indicatorBottomPadding),
                           pagerState = pagerState)
    }
}

@Preview(showBackground = true)
@Composable
fun PreViewImagePager() {
    var feedItemPageEvent by remember { mutableStateOf(FeedItemPageEvent(0, false, false)) }
    ImagePager(/*Preview*/
        images = arrayListOf(
            "http://sarang628.iptime.org:89/review_images/0/0/2023-06-20/11_15_27_247.png",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://sarang628.iptime.org:89/8.png",
            "http://sarang628.iptime.org:89/restaurants/1-1.jpeg",
            "https://samplelib.com/lib/preview/mp4/sample-5s.mp4"
        ),
        onImage = {},
        onPage = {
            feedItemPageEvent = it
        }
    )
    //Text("$feedItemPageEvent")
}