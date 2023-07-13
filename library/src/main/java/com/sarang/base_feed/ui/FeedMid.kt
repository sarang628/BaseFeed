package com.sarang.base_feed.ui

import TorangAsyncImage
import android.util.Log
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.basefeed.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemFeedMid(img: List<String>?, onImage: ((Int) -> Unit)? = null) {
    val pagerState = rememberPagerState(0)
    if (img == null)
        return
    Column(modifier = Modifier.height(460.dp)) {
        FeedPager(pagerState = pagerState, img = img)
        PagerIndicator(pagerState = pagerState, img = img)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedPager(
    pagerState: PagerState, img: List<String>?
) {
    if (img == null)
        return

    HorizontalPager(
        pageCount = img.size,
        state = pagerState,
    ) { page ->
        val state: MutableState<Int> = remember { mutableStateOf(0) }
        val scope = rememberCoroutineScope()
        // Our page content
        Row(
            modifier = Modifier
                .size(450.dp)
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //이미지가 정상적으로 로드 되었을때는 크롭하여 화면에 꽉차게 하고싶고
            TorangAsyncImage(
                url = img[page],
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            )
            // 그렇지 않을때는 작은 이미지로 오류를 처리하고 싶어서 아래와 같이 구현
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
                "https://www.naver.com",
                "http://sarang628.iptime.org:89/review_images/0/0/2023-06-20/11_15_27_247.png",
                "http://sarang628.iptime.org:89/8.png",
                "http://sarang628.iptime.org:89/restaurants/1-1.jpeg",
                "",
                ""
            )
        )
    }

}

@Preview
@Composable
fun TestAsyncImage() {
    AsyncImage(
        model =
        //"http://sarang628.iptime.org:89/8.png",
//        "http://sarang628.iptime.org:89/review_images/0/0/2023-06-20/11_15_27_247.png",
        "http://sarang628.iptime.org:89/restaurants/1-1.jpeg",
        contentDescription = "",
        modifier = Modifier.width(100.dp)
    )
}