package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.sarang.torang.compose.FeedList
import com.sarang.torang.compose.feed.PreviewFeed
import com.sarang.torang.compose.feed.internal.components.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.LocalFeedImageLoader
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.repository.test.FeedRepositoryTest
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tag = "__MainActivity"

    @Inject
    lateinit var feedRepository: FeedRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TorangTheme {
                Surface(Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)) {
                    CompositionLocalProvider(
                        LocalFeedImageLoader provides { CustomFeedImageLoader(showLog = true).invoke(it) },
                        LocalExpandableTextType provides CustomExpandableTextType
                    ) {
                        FeedList(showLog = true){
                            FeedRepositoryTest(feedRepository = feedRepository)
                        }
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun PreviewFeed1() {
    TorangTheme {
        Surface(Modifier.background(MaterialTheme.colorScheme.background)) {
            PreviewFeed()
        }
    }
}


