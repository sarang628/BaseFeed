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
import com.sryang.base.feed.compose.feed.Feeds
import com.sryang.base.feed.uistate.isEmpty
import com.sryang.base.feed.uistate.isVisibleList
import com.sryang.base.feed.uistate.testFeedUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val feedUiState = testFeedUiState()
            TorangTheme {
                val context = LocalContext.current
                Surface(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    Feeds(
                        profileImageServerUrl = "http://sarang628.iptime.org:89/profile_images/",
                        imageServerUrl = "http://sarang628.iptime.org:89/review_images/",
                        list = feedUiState.reviews,
                        isRefreshing = feedUiState.isRefreshing,
                        isLoaded = feedUiState.isLoaded,
                        isVisibleList = feedUiState.isVisibleList,
                        isEmpty = feedUiState.isEmpty,
                        onProfile = { },
                        onLike = { },
                        onComment = { },
                        onShare = { },
                        onFavorite = { },
                        onMenu = { },
                        onName = { },
                        onRestaurant = { },
                        onImage = { },
                        onRefresh = {},
                        onBottom = {
                            Toast.makeText(context, "onBottom", Toast.LENGTH_SHORT).show()
                        },
                        ratingBar = {
                            RatingBar(rating = it)
                        }
                    )
                }
            }
        }
    }
}