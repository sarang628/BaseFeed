package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.FeedItem
import com.sarang.torang.compose.feed.PreviewFeed
import com.sarang.torang.compose.feed.internal.components.ImagePagerWithIndicator
import com.sarang.torang.compose.feed.internal.components.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.LocalFeedImageLoader
import com.sarang.torang.di.basefeed.CustomExpandableTextType
import com.sarang.torang.di.basefeed.CustomFeedImageLoader
import com.sarang.torang.di.image.ZoomState
import com.sarang.torang.di.basefeed.toReview
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.repository.FeedRepositoryTest
import com.sarang.torang.ui.theme.ThemePreviews
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tag = "__MainActivity"

    @Inject
    lateinit var feedRepository: FeedRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TorangTheme {
                Surface(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                    CompositionLocalProvider(
                        LocalFeedImageLoader provides CustomFeedImageLoader,
                        LocalExpandableTextType provides CustomExpandableTextType
                    ) {
                        Test()
                    }
                }
            }
        }
    }

    @Composable
    fun Test(){
        val zoomStateForOnlyOutsideImage = remember { ZoomState() }
        val list by feedRepository.feeds.collectAsState(initial = ArrayList())
        val height = LocalConfiguration.current.screenHeightDp
        var position by remember { mutableStateOf("0") }
        val listState = rememberLazyListState()

        LaunchedEffect(key1 = position) {
            try {
                listState.animateScrollToItem(Integer.parseInt(position))
            } catch (e: Exception) {

            }
        }

        Column(Modifier.verticalScroll(rememberScrollState())) {
            OutlinedTextField(value = position, onValueChange = { position = it })
            Box(modifier = Modifier.height(height.dp - 30.dp)) {
                LazyColumn {
                    items(list.size) {
                        FeedItem(
                            uiState = list[it].toReview(),
                            pageScrollAble = false
                        )
                    }
                }

                if (zoomStateForOnlyOutsideImage.isZooming.value) {
                    Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.20f))) {
                        val offsetX = with(LocalDensity.current) { zoomStateForOnlyOutsideImage.bounds.value?.left?.toDp() ?: 0.dp }
                        val offsetY = with(LocalDensity.current) { zoomStateForOnlyOutsideImage.bounds.value?.top?.toDp() ?: 0.dp }
                        provideTorangAsyncImage().invoke(
                            Modifier.offset(offsetX, offsetY).size(400.dp)
                                .graphicsLayer {
                                    scaleX = zoomStateForOnlyOutsideImage.scale.value
                                    scaleY = zoomStateForOnlyOutsideImage.scale.value
                                    translationX = zoomStateForOnlyOutsideImage.offsetX.value
                                    translationY = zoomStateForOnlyOutsideImage.offsetY.value
                                },
                            zoomStateForOnlyOutsideImage.url.value,
                            30.dp,
                            30.dp,
                            ContentScale.Crop
                        )
                    }
                }
            }
            FeedRepositoryTest(feedRepository = feedRepository)
        }
    }
}

@ThemePreviews
@Composable
fun PreviewFeed1() {
    TorangTheme {
        Surface(Modifier.background(MaterialTheme.colorScheme.background)) {
            PreviewFeed()
        }
    }
}


