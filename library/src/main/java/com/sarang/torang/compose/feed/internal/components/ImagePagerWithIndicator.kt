package com.sarang.torang.compose.feed.internal.components

import TorangAsyncImage
import TorangAsyncImage1
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaItem.fromUri
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.ui.PlayerView
import com.sarang.torang.compose.feed.internal.util.nonEffectclickable
import com.sarang.torang.compose.feed.internal.util.pinchZoom
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.AbstractMap.SimpleEntry

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
            modifier = Modifier.size(400.dp),
            state = pagerState,
            userScrollEnabled = scrollEnable
        ) { page ->

            val ext = images[page].substring(images[page].lastIndexOf("."))
            Log.d("__sryang", ext)
            if (ext.equals(".mp4")) {
                VideoPlayer(images[page])
            } else {
                TorangAsyncImage1(
                    model = images[page],
                    modifier = modifier
                        .size(400.dp)
                        .nonEffectclickable(onClick = { onImage.invoke(page) })
                        .pinchZoom {
                            scrollEnable = !it
                            isZooming?.invoke(it)
                        },
                    progressSize = progressSize,
                    errorIconSize = errorIconSize
                )
            }
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

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreViewItemFeedMid() {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        ImagePagerWithIndicator(
            /*Preview*/
            images = arrayListOf(
//                "https://www.naver.com",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "http://sarang628.iptime.org:89/review_images/0/0/2023-06-20/11_15_27_247.png",
                "http://sarang628.iptime.org:89/8.png",
                "http://sarang628.iptime.org:89/restaurants/1-1.jpeg",
                "https://samplelib.com/lib/preview/mp4/sample-5s.mp4"
//                "",
//                ""
            ),
            onImage = {},
        )
    }
}

@Preview
@Composable
fun test() {

}

@androidx.annotation.OptIn(UnstableApi::class)
@Preview
@Composable
fun PreviewVideoView() {
//    VideoPlayer(player, videoUrl = "https://samplelib.com/lib/preview/mp4/sample-5s.mp4")
    VideoPlayer(
        videoUrl = "http://sarang628.iptime.org:89/review_images/0/0/2023-06-20/11_15_27_247.png"
    )
}

@androidx.annotation.OptIn(UnstableApi::class) @Composable
fun VideoPlayer(videoUrl: String) {
    val context = LocalContext.current
    var player = ExoPlayer.Builder(context).build()
    var isPlaying by remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()

    //val systemUiController = LocalSysUiController.current

    DisposableEffect(Unit) {
        onDispose {
            // 비디오 재생이 끝날 때 리소스 해제 등의 작업을 수행할 수 있습니다.
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                setPlayer(player)
                hideController()
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    // 비디오를 로드하고 재생합니다.
    val mediaItem = fromUri(videoUrl)
    player.setMediaItem(mediaItem)
    player.playWhenReady = true
    player.prepare()
    LaunchedEffect(key1 = "", block = {
        coroutine.launch {
            delay(10)
            player.play()
        }
    })

    // 앱이 백그라운드로 갈 때 ExoPlayer를 정지합니다.
    ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onBackground() {
            player.playWhenReady = false
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onForeground() {
            player.playWhenReady = isPlaying
        }
    })
}