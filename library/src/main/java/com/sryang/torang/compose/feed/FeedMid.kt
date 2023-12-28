package com.sryang.torang.compose.feed

import TorangAsyncImage
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.stopScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FeedMid(
    imgs: List<String>,                 // 이미지 리스트
    onImage: (Int) -> Unit,             // 이미지 클릭 이벤트
    progressSize: Dp = 50.dp,           // 이미지 로드 프로그래스 크기
    errorIconSize: Dp = 50.dp,          // 이미지 로드 에러 아이콘 크기
    isZooming: ((Boolean) -> Unit)? = null
) {
    val pagerState = rememberPagerState(pageCount = { imgs.size })
    var scrollEnable by remember { mutableStateOf(true) }
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = scrollEnable
    ) { page ->
        TorangAsyncImage(
            model = imgs[page],
            modifier = Modifier
                .size(450.dp)
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable1(onClick = { onImage.invoke(page) })
                .pinchZoom {
                    scrollEnable = !it
                    isZooming?.invoke(it)
                },
            progressSize = progressSize,
            errorIconSize = errorIconSize
        )
    }
    PagerIndicator(pagerState = pagerState, img = imgs)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FeedMid1(
    imgs: List<String>,                 // 이미지 리스트
    onImage: (Int) -> Unit,             // 이미지 클릭 이벤트
    progressSize: Dp = 50.dp,           // 이미지 로드 프로그래스 크기
    errorIconSize: Dp = 50.dp,          // 이미지 로드 에러 아이콘 크기
    isZooming: ((Boolean) -> Unit)? = null
) {
    val pagerState = rememberPagerState(pageCount = { imgs.size })
    var scrollEnable by remember { mutableStateOf(true) }
    val scale = remember { mutableFloatStateOf(1f) }
    val offsetX = remember { mutableFloatStateOf(1f) }
    val offsetY = remember { mutableFloatStateOf(1f) }
    val plantImageZIndex = remember { mutableFloatStateOf(1f) }
    val maxScale = remember { mutableFloatStateOf(1f) }
    val minScale = remember { mutableFloatStateOf(3f) }
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = scrollEnable
    ) { page ->
        TorangAsyncImage(
            model = imgs[page],
            modifier = Modifier
                .size(450.dp)
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable1(onClick = { onImage.invoke(page) })
                .pointerInput(Unit) {
                    awaitEachGesture {
                        awaitFirstDown()
                        do {
                            Log.d("pinchZoom", "pinchZoom")
                            val event = awaitPointerEvent()
                            scale.value *= event.calculateZoom()
                            if (scale.value > 1) {
                                plantImageZIndex.value = 5f
                                val offset = event.calculatePan()
                                offsetX.value += offset.x
                                offsetY.value += offset.y
                            } else {

                            }
                        } while (event.changes.any { it.pressed })
                        if (currentEvent.type == PointerEventType.Release) {
                            scale.value = 1f
                            offsetX.value = 1f
                            offsetY.value = 1f
                            plantImageZIndex.value = 1f
                        }
                    }
                }
                .graphicsLayer {
                    scaleX = maxOf(maxScale.value, minOf(minScale.value, scale.value))
                    scaleY = maxOf(maxScale.value, minOf(minScale.value, scale.value))
                    translationX = offsetX.value
                    translationY = offsetY.value
                },
            progressSize = progressSize,
            errorIconSize = errorIconSize
        )
    }
    PagerIndicator(pagerState = pagerState, img = imgs)
}

@Composable
fun Modifier.pinchZoom(onPinchZoom: (Boolean) -> Unit): Modifier {
    val scale = remember { mutableFloatStateOf(1f) }
    val offsetX = remember { mutableFloatStateOf(1f) }
    val offsetY = remember { mutableFloatStateOf(1f) }
    val plantImageZIndex = remember { mutableFloatStateOf(1f) }
    val maxScale = remember { mutableFloatStateOf(1f) }
    val minScale = remember { mutableFloatStateOf(3f) }
    this
        .pointerInput(Unit) {
            awaitEachGesture {
                awaitFirstDown()
                do {
                    Log.d("pinchZoom", "pinchZoom")
                    val event = awaitPointerEvent()
                    scale.value *= event.calculateZoom()
                    if (scale.value > 1) {
                        onPinchZoom.invoke(true)
                        plantImageZIndex.value = 5f
                        val offset = event.calculatePan()
                        offsetX.value += offset.x
                        offsetY.value += offset.y
                    } else {
                        onPinchZoom.invoke(false)
                    }
                } while (event.changes.any { it.pressed })
                if (currentEvent.type == PointerEventType.Release) {
                    scale.value = 1f
                    offsetX.value = 1f
                    offsetY.value = 1f
                    plantImageZIndex.value = 1f
                }
            }
        }
        .graphicsLayer {
            scaleX = maxOf(maxScale.value, minOf(minScale.value, scale.value))
            scaleY = maxOf(maxScale.value, minOf(minScale.value, scale.value))
            translationX = offsetX.value
            translationY = offsetY.value
        }
    return this
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    img: List<String>?, pagerState: PagerState
) {
    if (img == null)
        return

    Row(
        Modifier
            .height(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(img.size) { iteration ->
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

@Preview
@Composable
fun PreViewItemFeedMid() {
    Column(Modifier.fillMaxSize().background(Color.Gray)) {
        FeedMid1(
            arrayListOf(
//                "https://www.naver.com",
                "http://sarang628.iptime.org:89/review_images/0/0/2023-06-20/11_15_27_247.png",
                "http://sarang628.iptime.org:89/8.png",
                "http://sarang628.iptime.org:89/restaurants/1-1.jpeg",
//                "",
//                ""
            ),
            progressSize = 30.dp,
            errorIconSize = 30.dp,
            onImage = {},
        )
    }
}