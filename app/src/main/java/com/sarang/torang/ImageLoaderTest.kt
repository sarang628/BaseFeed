package com.sarang.torang

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sarang.torang.compose.component.type.FeedImageLoaderData
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.repository.feed.FeedLoadRepository
import kotlin.random.Random

@Composable
fun ImageLoaderTest(
    feedLoadRepository: FeedLoadRepository
){
    var pictures : List<String?> by remember { mutableStateOf(emptyList<String>()) }
    var random by remember { mutableStateOf(-1) }

    LaunchedEffect(Unit) {
        feedLoadRepository.feeds.collect {
            it?.let {
                pictures = it.flatMap { it.images.map { it.pictureUrl } }
            }

            if(pictures.isNotEmpty())
                random = Random.nextInt(pictures.size)
        }
    }
    Column {
        Button({
            if(pictures.isNotEmpty())
                random = Random.nextInt(pictures.size)
        }) {
            Text("Next")
        }

        if(random > 0) {
            CustomFeedImageLoader().invoke(
                FeedImageLoaderData(
                    url = "http://sarang628.iptime.org:89/review_images/${pictures[random]}"
                )
            )
        }
    }
}