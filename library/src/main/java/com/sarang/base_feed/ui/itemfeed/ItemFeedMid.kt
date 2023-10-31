package com.sarang.base_feed.ui.itemfeed

import TorangAsyncImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemFeedMid(
    img: List<String>,
    onImage: ((Int) -> Unit)? = null,
    progressSize: Dp = 50.dp,
    errorIconSize: Dp = 50.dp,
    imageServerUrl: String = ""
) {
    val pagerState = rememberPagerState(pageCount = { img.size })
    Column(modifier = Modifier.height(460.dp)) {
        FeedPager(
            pagerState = pagerState,
            img = img,
            progressSize = progressSize,
            errorIconSize = errorIconSize,
            imageServerUrl = imageServerUrl
        )
        PagerIndicator(pagerState = pagerState, img = img)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedPager(
    pagerState: PagerState,
    img: List<String>,
    progressSize: Dp = 50.dp,
    errorIconSize: Dp = 50.dp,
    imageServerUrl: String = ""
) {
    HorizontalPager(
        state = pagerState,
    ) { page ->
        val state: MutableState<Int> = remember { mutableStateOf(0) }
        val scope = rememberCoroutineScope()
        Row(
            modifier = Modifier
                .size(450.dp)
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TorangAsyncImage(
                model = imageServerUrl + img[page],
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                progressSize = progressSize,
                errorIconSize = errorIconSize
            )
        }
    }
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
    Column {
        ItemFeedMid(
            arrayListOf(
//                "https://www.naver.com",
//                "http://sarang628.iptime.org:89/review_images/0/0/2023-06-20/11_15_27_247.png",
//                "http://sarang628.iptime.org:89/8.png",
//                "http://sarang628.iptime.org:89/restaurants/1-1.jpeg",
//                "",
//                ""
            ),
            progressSize = 30.dp,
            errorIconSize = 30.dp
        )
    }
}