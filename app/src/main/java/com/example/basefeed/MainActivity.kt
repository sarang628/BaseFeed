package com.example.basefeed

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.library.RatingBar
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sryang.torang.compose.feed.Feeds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorangTheme {
                val context = LocalContext.current
                Surface(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Feeds(
                        list = listOf(),
                        isRefreshing = false,
                        isEmpty = true,
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