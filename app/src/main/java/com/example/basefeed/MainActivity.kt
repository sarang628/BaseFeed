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
import com.sarang.base_feed.uistate.FeedBottomUIState
import com.sarang.base_feed.uistate.FeedTopUIState
import com.sarang.base_feed.uistate.FeedUiState
import kotlinx.coroutines.flow.MutableStateFlow

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
                        onRefresh = {}
                    )
                }
            }
        }
    }

    private fun testFeedUiState(): FeedUiState {
        return FeedUiState(
            reviewId = 0,
            itemFeedBottomUiState = testFeedBottomUIState(),
            itemFeedTopUiState = testFeedTopUIState()
        )
    }

    fun testFeedBottomUIState(
    ): FeedBottomUIState {
        return FeedBottomUIState(
            reviewId = 0,
            likeAmount = 0,
            commentAmount = 0,
            author = "",
            author1 = "",
            author2 = "",
            comment = "",
            comment1 = "",
            comment2 = "",
            isLike = false,
            isFavorite = false,
            visibleLike = true,
            visibleComment = false,
            contents = ""
        )
    }

    fun testFeedTopUIState(): FeedTopUIState {
        return FeedTopUIState(
            reviewId = 0,
            userId = 0,
            name = "",
            restaurantName = "",
            rating = 0f,
            profilePictureUrl = ""
        )
    }
}
