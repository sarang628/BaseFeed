import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sarang.torang.compose.feed.Feed
import com.sarang.torang.data.basefeed.Comment
import com.sarang.torang.data.basefeed.Restaurant
import com.sarang.torang.data.basefeed.Review
import com.sarang.torang.data.basefeed.User
import com.sarang.torang.data.basefeed.testReviewData
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
                review = Review(
                    reviewId = 0,
                    reviewImages = ArrayList<String>().apply {
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                        add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
                    },
                    user = User(
                        userId = 0,
                        name = "name",
                        profilePictureUrl = "1/2023-09-14/10_44_39_302.jpeg"
                    ),
                    restaurant = Restaurant(
                        restaurantId = 1,
                        restaurantName = "restaurantName",
                    ),
                    rating = 3.5f,
                    contents = "" +
                            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
                            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
                            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
                            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
                            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
                            "",
                    comments = ArrayList<Comment>().apply {
                        add(Comment("author", "comment"))
                    },
                    commentAmount = 0,
                    isLike = false,
                    isFavorite = false,
                    likeAmount = 100,
                    onComment = {},
                    onFavorite = {},
                    onImage = {},
                    onLike = {},
                    onMenu = {},
                    onName = {},
                    onProfile = {},
                    onRestaurant = {},
                    onShare = {},
                    createDate = "2022-01-01 22:00:00"
                )
            )
        }
    }

    @Test
    fun checkName() {
        val data = testReviewData()
        composeRules.setContent {
            Feed(review = data)
        }
        composeRules.onNodeWithText(data.user.name).assertIsDisplayed()
    }

    @Test
    fun checkRestaurantName() {
        val data = testReviewData()
        composeRules.setContent {
            Feed(review = data)
        }
        composeRules.onNodeWithText(data.restaurant.restaurantName).assertIsDisplayed()
    }

    @Test
    fun checkContents() {
        val data = testReviewData()
        composeRules.setContent {
            Feed(review = data)
        }
        composeRules.onNodeWithText("name contents")
            .assertIsDisplayed()
    }

    @Test
    fun checkMenu() {
        val data = testReviewData()
        composeRules.setContent {
            Feed(review = data)
        }
        composeRules.onNodeWithContentDescription("menu").assertIsDisplayed()
    }

    @Test
    fun checkLike() {
        val data = testReviewData()
        composeRules.setContent {
            Feed(review = data)
        }
        composeRules.onNodeWithContentDescription("like").assertIsDisplayed()
    }

    @Test
    fun checkComment() {
        val data = testReviewData()
        composeRules.setContent {
            Feed(review = data)
        }
        composeRules.onNodeWithContentDescription("comment").assertIsDisplayed()
    }

    @Test
    fun checkShare() {
        val data = testReviewData()
        composeRules.setContent {
            Feed(review = data)
        }
        composeRules.onNodeWithContentDescription("share").assertIsDisplayed()
    }

    @Test
    fun checkFavorite() {
        val data = testReviewData()
        composeRules.setContent {
            Feed(review = data)
        }
        composeRules.onNodeWithContentDescription("favorite").assertIsDisplayed()
    }
}