import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sryang.torang.compose.feed.Feed
import com.sryang.torang.data.basefeed.testReviewData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedUITest {

    @get:Rule
    val composeRules = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test() {
        composeRules.setContent {
            Feed(
                review = testReviewData(),
                onProfile = { /*TODO*/ },
                onLike = { /*TODO*/ },
                onComment = { /*TODO*/ },
                onShare = { /*TODO*/ },
                onFavorite = { /*TODO*/ },
                onMenu = { /*TODO*/ },
                onName = { /*TODO*/ },
                onRestaurant = { /*TODO*/ },
                onImage = {},
                ratingBar = {}
            )
        }
    }
}