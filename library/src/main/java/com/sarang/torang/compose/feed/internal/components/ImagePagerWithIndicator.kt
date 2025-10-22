package com.sarang.torang.compose.feed.internal.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.d
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * ImagePager 와 Indicator
 * @param modifier modifier
 * @param pagerState pagerState
 * @param images 이미지 리스트
 * @param onImage 이미지 클릭 이벤트
 * @param showIndicator indicator 표시 여부
 * @param videoPlayer video player compose
 * @param image image compose
 * @param height height
 * @param zoomState 이미지 zoom 상태
 */
@Composable
fun ImagePagerWithIndicator(
    modifier                : Modifier          = Modifier,
    tag                     : String            = "__ImagePagerWithIndicator",
    showLog                 : Boolean           = false,
    pagerState              : PagerState        = rememberPagerState { images.size },
    images                  : List<String>      = listOf(),
    onImage                 : (Int) -> Unit     = { Log.i(tag, "onImage callback is not set page : $it") },
    showIndicator           : Boolean           = true,
    height                  : Dp                = 400.dp,
    scrollEnable            : Boolean           = true,
    indicatorBottomPadding  : Dp                = 12.dp,
    onPage : (Int, Boolean, Boolean) -> Unit    = { page, isFirst, isLast -> Log.w(tag, "onPage callback is not set page: $page isFirst: $isFirst isLast: $isLast") }
) {

    val pagerState: PagerState = rememberPagerState { images.size }

    showLog.d(tag, "height : $height")

    LaunchedEffect(pagerState) {
        snapshotFlow{pagerState.currentPage}
            .distinctUntilChanged() // 중복된 값 방지
            .collect{ onPage.invoke(it, it == 0, it == images.size - 1) }
    }

    Box(modifier = modifier.layoutId("reviewImages")) {
        HorizontalPager(
            modifier = Modifier.height(height),
            state = pagerState,
            userScrollEnabled = scrollEnable
        ) { page ->
            val ext = images[page].substring(images[page].lastIndexOf("."))
            if (ext == ".m3u8") { LocalVideoPlayerType.current.invoke(images[page]) } else {
                LocalFeedImageLoader.current.invoke(
                    FeedImageLoaderData(
                        modifier = modifier
                            .testTag("imgReview")
                            .fillMaxSize()
                            .nonEffectclickable(onClick = { onImage.invoke(page); }),
                        url = images[page],
                        contentScale = ContentScale.Crop,
                        height = height
                    )
                )
            }
        }
        if (showIndicator)
            PagerIndicator(Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = indicatorBottomPadding),pagerState = pagerState, count = images.size)
    }
}


@Preview
@Composable
fun PagerIndicator(modifier: Modifier = Modifier, count: Int = 0, pagerState: PagerState = rememberPagerState {count})  {
    if (count > 1)
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(count) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.White else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(5.dp)
                )
            }
        }
}

@Preview(showBackground = true)
@Composable
fun PreViewImagePagerWithIndicator() {
    ImagePagerWithIndicator(/*Preview*/
        images = arrayListOf(
            "http://sarang628.iptime.org:89/review_images/0/0/2023-06-20/11_15_27_247.png",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://sarang628.iptime.org:89/8.png",
            "http://sarang628.iptime.org:89/restaurants/1-1.jpeg",
            "https://samplelib.com/lib/preview/mp4/sample-5s.mp4"
        ),
        onImage = {}
    )
}