package com.sarang.torang

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.repository.feed.FeedFlowRepository
import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.repository.feed.FeedRepository
import com.sarang.torang.repository.test.feed.FeedRepositoryTestScreen

@Preview
@Composable
internal fun Navigation(
    feedRepository: FeedRepository,
    feedLoadRepository: FeedLoadRepository,
    feedFlowRepository: FeedFlowRepository,
    feed : @Composable () -> Unit = {},
    feedList : @Composable () -> Unit = {},
    feedGridPictureList : @Composable () -> Unit = {},
    imageLoaderTest : @Composable () -> Unit = {}
){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "menu"){
        composable("menu"){ Menu(navController) }
        composable("Feed") { feed() }
        composable("FeedList"){ feedList() }
        composable("FeedGridPictureList"){ feedGridPictureList() }
        composable("ImageLoaderTest") { imageLoaderTest() }
        composable("FeedRepository"){
            FeedRepositoryTestScreen(feedRepository     = feedRepository,
                feedLoadRepository = feedLoadRepository,
                feedFlowRepository = feedFlowRepository)
        }

        composable("FeedMediaPagerTest"){
            FeedMediaPagerTest(feedLoadRepository = feedLoadRepository)
        }
    }
}