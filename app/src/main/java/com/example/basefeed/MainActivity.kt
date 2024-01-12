package com.example.basefeed

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.basefeed.di.basefeed.review
import com.example.basefeed.di.basefeed.toFeedData
import com.example.commonwidgets.torangcomposepack.AndroidViewRatingBar
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sryang.torang.compose.feed.Feeds
import com.sryang.torang.uistate.FeedsUiState
import com.sryang.torang_repository.repository.FeedRepository
import com.sryang.torang_repository.repository.FeedRepositoryTest
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var feedRepository: FeedRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isRefreshing by remember { mutableStateOf(false) }

            TorangTheme {
                val context = LocalContext.current
                val height = LocalConfiguration.current.screenHeightDp
                Surface(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val list by feedRepository.feeds.collectAsState(initial = arrayListOf())
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        Box(modifier = Modifier.height(height.dp - 30.dp)) {
                            Feeds(
                                isRefreshing = isRefreshing,
                                onRefresh = {
                                    isRefreshing = true
                                },
                                onBottom = {
                                    Toast.makeText(context, "onBottom", Toast.LENGTH_SHORT).show()
                                },
                                ratingBar = { modifier, rating ->
                                    AndroidViewRatingBar(
                                        modifier = modifier,
                                        rating = rating,
                                        isSmall = true,
                                        changable = false
                                    )
                                },
                                feedsUiState = FeedsUiState.Success(
                                    list.map { it.toFeedData().review() }
                                )
                            )
                        }
                        FeedRepositoryTest(feedRepository = feedRepository)
                    }
                }
            }
        }
    }
}