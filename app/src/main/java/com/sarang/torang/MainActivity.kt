package com.sarang.torang

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.torang.compose.feed.Feeds
import com.sarang.torang.compose.feed.PreViewFeed
import com.sarang.torang.di.basefeed.review
import com.sarang.torang.ui.theme.ThemePreviews
import com.sarang.torang.uistate.FeedsUiState
import com.sryang.torang_repository.repository.FeedRepository
import com.sryang.torang_repository.repository.FeedRepositoryTest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var feedRepository: FeedRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        //WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            var isRefreshing by remember { mutableStateOf(false) }
            val coroutine = rememberCoroutineScope()
            var feedsUiState: FeedsUiState by remember { mutableStateOf(FeedsUiState.Loading) }
            val list by feedRepository.feeds.collectAsState(initial = arrayListOf()) // repository로 부터 feed 리스트 가져옴
            val context = LocalContext.current

            LaunchedEffect(key1 = list, block = {
                feedsUiState = FeedsUiState.Loading
                delay(2000)
                feedsUiState = FeedsUiState.Success(
                    list.map // repository로부터 받은 리스트를 basefeed에서 제공한 데이터로 변환
                    {
                        it.review(
                            onComment = {
                                Toast.makeText(context, "onComment", Toast.LENGTH_SHORT).show()
                            },
                            onShare = {
                                Toast.makeText(context, "onShare", Toast.LENGTH_SHORT).show()
                            },
                            onMenu = {
                                Toast.makeText(context, "onMenu", Toast.LENGTH_SHORT).show()
                            },
                            onName = {
                                Toast.makeText(context, "onName", Toast.LENGTH_SHORT).show()
                            },
                            onRestaurant = {
                                Toast.makeText(context, "onRestaurant", Toast.LENGTH_SHORT).show()
                            },
                            onImage = {
                                Toast.makeText(context, "onImage", Toast.LENGTH_SHORT).show()
                            },
                            onProfile = {
                                Toast.makeText(context, "onProfile", Toast.LENGTH_SHORT).show()
                            },
                            onFavorite = {
                                Toast.makeText(context, "onFavorite", Toast.LENGTH_SHORT).show()
                            },
                            onLike = {
                                Toast.makeText(context, "onLike", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )
            })

            TorangTheme {
                val context = LocalContext.current
                val height = LocalConfiguration.current.screenHeightDp
                Surface(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        Box(modifier = Modifier.height(height.dp - 30.dp)) {
                            Feeds(
                                isRefreshing = isRefreshing,
                                onRefresh = {
                                    coroutine.launch {
                                        isRefreshing = true
                                        delay(1000)
                                        isRefreshing = false
                                    }
                                },
                                onBottom = {
                                    Toast.makeText(context, "onBottom", Toast.LENGTH_SHORT).show()
                                },
                                feedsUiState = feedsUiState,
                                progressTintColor = Color(0xffe6cc00)
                            )
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
        val context = LocalContext.current
        val height = LocalConfiguration.current.screenHeightDp
        Surface(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            PreViewFeed()
        }
    }
}