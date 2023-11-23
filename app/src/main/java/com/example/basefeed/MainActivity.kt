package com.example.basefeed

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.library.RatingBar
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.base_feed.ui.Feeds
import com.sarang.base_feed.uistate.FeedUiState
import com.sarang.base_feed.uistate.testFeedUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorangTheme {
                val context = LocalContext.current
                Surface(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    Feeds(
                        list = (ArrayList<FeedUiState>().apply {
//                            add(testFeedUiState())
//                            add(testFeedUiState())
//                            add(testFeedUiState())
//                            add(testFeedUiState())
//                            add(testFeedUiState())
//                            add(testFeedUiState())
//                            add(testFeedUiState())
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
                        onBottom = {
                            Toast.makeText(context, "onBottom", Toast.LENGTH_SHORT).show()
                        },
                        profileImageServerUrl = "http://sarang628.iptime.org:89/profile_images/",
                        imageServerUrl = "http://sarang628.iptime.org:89/review_images/",
                        ratingBar = {
                            RatingBar(rating = it)
                        },
                        isLoaded = true
                    )
                }
            }
        }
    }
}