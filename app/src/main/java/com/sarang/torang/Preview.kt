package com.sarang.torang

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.sarang.torang.compose.feed.PreviewFeed
import com.sarang.torang.compose.feed.internal.components.PreViewFeedMediaPager
import com.sarang.torang.compose.feed.internal.components.type.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.type.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.components.type.LocalVideoPlayerType
import com.sarang.torang.data.ReviewAndImage
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.di.basefeed_di.CustomVideoPlayerType
import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sryang.torang.ui.TorangTheme
import kotlinx.coroutines.flow.stateIn

@Preview
@Composable
fun FeedMediaPagerTest(){
    TorangTheme {
        CompositionLocalProvider(LocalFeedImageLoader     provides { CustomFeedImageLoader(showLog = true).invoke(it) },
            LocalExpandableTextType  provides CustomExpandableTextType,
            LocalVideoPlayerType     provides CustomVideoPlayerType()) {
            LazyColumn {
                items(10){
                    PreViewFeedMediaPager()
                }
            }
        }
    }
}

@Composable
fun FeedMediaPagerTest(feedLoadRepository: FeedLoadRepository){
    val coroutineScope = rememberCoroutineScope()

    var feed : List<ReviewAndImage> by remember { mutableStateOf(emptyList()) }
    LaunchedEffect(Unit) {
        feedLoadRepository.setLoadTrigger(true)
    }
    LaunchedEffect(Unit) {
        feedLoadRepository.feeds.stateIn(coroutineScope).collect {
            feed = it ?: emptyList()
        }
    }
    TorangTheme {
        CompositionLocalProvider(LocalFeedImageLoader     provides { CustomFeedImageLoader(showLog = true).invoke(it) },
            LocalExpandableTextType  provides CustomExpandableTextType,
            LocalVideoPlayerType     provides CustomVideoPlayerType()) {
            LazyColumn {
                items(feed){
                    PreViewFeedMediaPager(it.images.map {
                        BuildConfig.REVIEW_IMAGE_SERVER_URL + it.pictureUrl
                    })
                }
            }
        }
    }
}


@PreviewLightDark
@Composable
fun PreviewFeed1() {
    TorangTheme {
        PreviewFeed()
    }
}