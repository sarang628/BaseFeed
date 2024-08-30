package com.sarang.torang

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.torang.compose.feed.Feed
import com.sarang.torang.compose.feed.PreViewFeed
import com.sarang.torang.di.basefeed.toReview
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.repository.FeedRepositoryTest
import com.sarang.torang.ui.theme.ThemePreviews
import com.sryang.library.ExpandableText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var feedRepository: FeedRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var position by remember { mutableStateOf("0") }
            val listState = rememberLazyListState()

            LaunchedEffect(key1 = position) {
                try {
                    basefeedLog("${position}")
                    listState.animateScrollToItem(Integer.parseInt(position))
                } catch (e: Exception) {

                }
            }

            val list by feedRepository.feeds.collectAsState(initial = ArrayList())

            TorangTheme {
                val context = LocalContext.current
                val height = LocalConfiguration.current.screenHeightDp

                Surface(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        OutlinedTextField(value = position, onValueChange = { position = it })
                        Box(modifier = Modifier.height(height.dp - 30.dp)) {
                            LazyColumn {
                                items(list.size) {
                                    Feed(
                                        review = list[it].toReview(),
                                        imageLoadCompose = provideTorangAsyncImage(),
                                        onImage = {},
                                        onMenu = {}, onProfile = {},
                                        onLike = {
                                            Log.d("__MainActivity", "onLike: $it")
                                        },
                                        onComment = {},
                                        onShare = {},
                                        onFavorite = {},
                                        onName = {},
                                        isZooming = {},
                                        onRestaurant = {},
                                        onLikes = {},
                                        expandableText = { modifier, nickName, text, onProfile ->
                                            ExpandableText(
                                                modifier = modifier,
                                                nickName = nickName,
                                                text = text,
                                                onClickNickName = onProfile
                                            )
                                        },
                                        videoPlayer = { VideoPlayer1(videoUrl = it) }
                                    )
                                }
                            }
                        }
                        FeedRepositoryTest(feedRepository = feedRepository)
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun test() {
    TorangTheme {
        Surface(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            PreViewFeed()
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(videoUrl: String, player: ExoPlayer?) {
    var isPlaying by remember { mutableStateOf(false) }

    // 앱이 백그라운드로 갈 때 ExoPlayer를 정지합니다.
    ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onBackground() {
            player?.playWhenReady = false
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onForeground() {
            player?.playWhenReady = isPlaying
        }
    })
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer1(videoUrl: String) {
    Log.d("__sryang", "VideoPlayer1: $videoUrl")
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                }
            }, modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        )
    ) {
        onDispose { exoPlayer.release() }
    }
}
