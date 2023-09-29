package com.example.basefeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.basefeed.ui.theme.BaseFeedTheme
import com.sarang.base_feed.ui.Feeds
import com.sarang.base_feed.uistate.FeedUiState
import com.sarang.base_feed.uistate.testFeedUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseFeedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Feeds(
                        list = (ArrayList<FeedUiState>().apply {
                            add(testFeedUiState())
                            add(testFeedUiState())
                            add(testFeedUiState())
                            add(testFeedUiState())
                            add(testFeedUiState())
                            add(testFeedUiState())
                            add(testFeedUiState())
                        }),
                        onProfile = { },
                        onLike = { },
                        onComment = { },
                        onShare = { },
                        onFavorite = { },
                        onMenu = { },
                        onName = { },
                        onRestaurant = { },
                        onImage = { },
                        isRefreshing = false,
                        onRefresh = {},
                        profileImageServerUrl = "http://sarang628.iptime.org:89/profile_images/",
                        imageServerUrl = "http://sarang628.iptime.org:89/review_images/"
                    )
                }
            }
        }
    }
}
