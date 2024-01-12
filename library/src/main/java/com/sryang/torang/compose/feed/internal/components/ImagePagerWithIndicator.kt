package com.sryang.torang.compose.feed.internal.components

import TorangAsyncImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sryang.torang.compose.feed.internal.util.clickable1
import com.sryang.torang.compose.feed.internal.util.pinchZoom

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ImagePagerWithIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState { images.size },
    images: List<String>,                 // 이미지 리스트
    onImage: (Int) -> Unit,             // 이미지 클릭 이벤트
    progressSize: Dp = 30.dp,           // 이미지 로드 프로그래스 크기
    errorIconSize: Dp = 30.dp,          // 이미지 로드 에러 아이콘 크기
    isZooming: ((Boolean) -> Unit)? = null,
    showIndicator: Boolean = false
) {
    var scrollEnable by remember { mutableStateOf(true) }
    Column(modifier = modifier) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            userScrollEnabled = scrollEnable
        ) { page ->
            TorangAsyncImage(
                model = images[page],
                modifier = modifier
                    .size(450.dp)
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
        if (showIndicator)
            PagerIndicator(
                modifier = Modifier.fillMaxSize(),
                pagerState = pagerState,
                count = images.size
            )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    pagerState: PagerState
) {
    if (count > 0)
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center
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

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreViewItemFeedMid() {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        ImagePagerWithIndicator(
            images = arrayListOf(
//                "https://www.naver.com",
                "http://sarang628.iptime.org:89/review_images/0/0/2023-06-20/11_15_27_247.png",
                "http://sarang628.iptime.org:89/8.png",
                "http://sarang628.iptime.org:89/restaurants/1-1.jpeg",
//                "",
//                ""
            ),
            onImage = {},
        )
    }
}