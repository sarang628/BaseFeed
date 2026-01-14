package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.compose.FeedList
import com.sarang.torang.compose.feed.PreviewFeed
import com.sarang.torang.compose.feed.internal.components.PreViewImagePager
import com.sarang.torang.compose.feed.internal.components.type.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.type.LocalFeedImageLoader
import com.sarang.torang.compose.feed.internal.components.type.LocalVideoPlayerType
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.di.basefeed_di.CustomVideoPlayerType
import com.sarang.torang.repository.feed.FeedFlowRepository
import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.repository.feed.FeedRepository
import com.sarang.torang.repository.test.feed.FeedRepositoryTest
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tag = "__MainActivity"
    @Inject lateinit var feedRepository: FeedRepository
    @Inject lateinit var feedFlowRepository: FeedFlowRepository
    @Inject lateinit var feedLoadRepository: FeedLoadRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    feedLoadRepository.setLoadTrigger(true)
                }
            }
            TorangTheme {
                            Surface(Modifier.fillMaxSize()
                                            .background(MaterialTheme.colorScheme.background))
                            {
                                Test()
                            }
                        }
                   }
    }

    @Composable
    fun Test(){
        val navController = rememberNavController()
        NavHost(navController, startDestination = "menu"){
            composable("FeedTest") {
                PreviewFeed()
            }
            composable("FeedList"){
                CompositionLocalProvider(LocalFeedImageLoader    provides { CustomFeedImageLoader(showLog = true).invoke(it) },
                                                   LocalExpandableTextType provides CustomExpandableTextType,
                                                   LocalVideoPlayerType provides CustomVideoPlayerType) {
                    FeedList()
                }
            }
            composable("menu"){
                Column {
                    Button({navController.navigate("FeedList")}) {
                        Text("FeedList")
                    }
                    Button({navController.navigate("FeedRepositoryTest")}) {
                        Text("FeedRepositoryTest")
                    }
                }
            }
            composable("FeedRepositoryTest"){
                FeedRepositoryTest(feedRepository = feedRepository,
                    feedLoadRepository = feedLoadRepository,
                    feedFlowRepository = feedFlowRepository)
            }

            composable("ImagePagerScrollTest"){
                ImagePagerScrollTest()
            }
        }
    }

    @Composable
    fun ImagePagerScrollTest(){
        LazyColumn {
            items(100){
                PreViewImagePager()
            }
        }
    }

    @PreviewLightDark
    @Composable
    fun PreviewFeed1() {
        TorangTheme {
            Surface(Modifier.background(MaterialTheme.colorScheme.background)) {
                PreviewFeed()
            }
        }
    }
}




