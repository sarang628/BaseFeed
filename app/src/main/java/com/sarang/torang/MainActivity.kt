package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.compose.FeedList
import com.sarang.torang.compose.feed.PreviewFeed
import com.sarang.torang.repository.feed.FeedFlowRepository
import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.repository.feed.FeedRepository
import com.sarang.torang.repository.test.feed.FeedRepositoryTestScreen
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
                                .background(MaterialTheme.colorScheme.background)) {
                    BaseTeedTest()
                }
            }
        }
    }

    @Preview
    @Composable
    fun BaseTeedTest(){
        val navController = rememberNavController()
        NavHost(navController, startDestination = "menu"){
            composable("menu"){
                Menu(navController)
            }
            composable("Feed") {
                PreviewFeed()
            }
            composable("FeedList"){
                FeedList()
            }
            composable("FeedRepository"){
                FeedRepositoryTestScreen(feedRepository     = feedRepository,
                                         feedLoadRepository = feedLoadRepository,
                                         feedFlowRepository = feedFlowRepository)
            }

            composable("ImagePagerScrollTest"){
                ImagePagerScrollTest()
            }
        }
    }
}




