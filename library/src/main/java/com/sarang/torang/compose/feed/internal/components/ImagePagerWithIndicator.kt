package com.sarang.torang.compose.feed.internal.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import com.sarang.torang.compose.feed.internal.util.pinchZoom

private val TAG = "__ImagePagerWithIndicator"

@Composable
fun ImagePagerWithIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState { images.size },
    images: List<String>,                 // 이미지 리스트
    onImage: (Int) -> Unit,             // 이미지 클릭 이벤트
    progressSize: Dp = 30.dp,           // 이미지 로드 프로그래스 크기
    errorIconSize: Dp = 30.dp,          // 이미지 로드 에러 아이콘 크기
    isZooming: ((Boolean) -> Unit)? = null,
    showIndicator: Boolean = false,
    videoPlayer: @Composable (String) -> Unit,
    image: @Composable (
        Modifier,
        String,
        Dp?,
        Dp?,
        ContentScale?,
    ) -> Unit,
    height: Dp = 400.dp,
    onPressed: () -> Unit = {},
    onReleased: () -> Unit = {},
) {
    var scrollEnable by remember { mutableStateOf(true) }
    Column(modifier = modifier) {
        HorizontalPager(
            modifier = Modifier
                .height(height)
                .pointerInput(PointerEventType.Press) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            // handle pointer event
                            if (event.type == PointerEventType.Press)
                                onPressed.invoke()
                        }
                    }
                }
                .pointerInput(PointerEventType.Release) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            if (event.type == PointerEventType.Release)
                                onReleased.invoke()
                        }
                    }
                },
            state = pagerState,
            userScrollEnabled = scrollEnable
        ) { page ->

            val ext = images[page].substring(images[page].lastIndexOf("."))
            if (ext == ".m3u8") {
                videoPlayer.invoke(images[page])
            } else {
                image.invoke(
                    modifier
                        .testTag("imgReview")
                        .fillMaxSize()
                        .nonEffectclickable(onClick = { onImage.invoke(page) })
                        .pinchZoom {
                            scrollEnable = !it
                            isZooming?.invoke(it)
                        },
                    images[page],
                    progressSize,
                    errorIconSize,
                    ContentScale.Crop
                )
            }
//        }
            if (showIndicator)
                PagerIndicator(
                    modifier = Modifier.fillMaxSize(),
                    pagerState = pagerState,
                    count = images.size
                )
        }
    }
}


@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    pagerState: PagerState,
) {
    if (count > 1)
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(count) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
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
        image = { _, _, _, _, _ -> },
        onImage = {},
        videoPlayer = {}
    )
}
