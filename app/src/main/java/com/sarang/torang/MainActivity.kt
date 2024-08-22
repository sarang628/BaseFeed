package com.sarang.torang

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
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
                                        }
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

