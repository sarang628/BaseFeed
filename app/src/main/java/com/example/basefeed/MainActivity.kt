package com.example.basefeed

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.library.RatingBar
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sryang.torang.compose.feed.Feeds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isRefreshing by remember { mutableStateOf(false) }
            val coroutine = rememberCoroutineScope()
            TorangTheme {
                val context = LocalContext.current
                Surface(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Feeds(
                        list = listOf(),
                        isRefreshing = isRefreshing,
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
                        onRefresh = {
                            isRefreshing = true
                            coroutine.launch {
                                delay(1000)
                                isRefreshing = false
                                Log.d("_MainActivity", "isRefreshing ${isRefreshing}")
                            }

                        },
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