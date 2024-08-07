import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sarang.torang.R
import com.sarang.torang.compose.feed.Feed
import com.sarang.torang.compose.feed.internal.components.summarizedString
import com.sarang.torang.data.basefeed.Comment
import com.sarang.torang.data.basefeed.Restaurant
import com.sarang.torang.data.basefeed.Review
import com.sarang.torang.data.basefeed.User
import com.sarang.torang.data.basefeed.formatedDate
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedUITest {

    @get:Rule
    val composeRules = createAndroidComposeRule<ComponentActivity>()

    private var onComment = false
    private var onFavorite = false
    private var onImage = false
    private var onLike = false
    private var onMenu = false
    private var onName = false
    private var onProfile = false
    private var onRestaurant = false
    private var onShare = false
    private var isZooming = false
    private var onLikes = false

    private val contents = "" +
            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
            "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
            ""

    private val createdDate = "2022-01-01 22:00:00"
    private val likeAmount = 100
    private val rating = 3.5f
    private val userName = "name"

    @Before
    fun init() {
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
                        name = userName,
                        profilePictureUrl = "1/2023-09-14/10_44_39_302.jpeg"
                    ),
                    restaurant = Restaurant(
                        restaurantId = 1,
                        restaurantName = "restaurantName",
                    ),
                    rating = rating,
                    contents = contents,
                    comments = ArrayList<Comment>().apply {
                        add(Comment("author", "comment"))
                    },
                    commentAmount = 0,
                    isLike = false,
                    isFavorite = false,
                    likeAmount = likeAmount,
                    createDate = createdDate
                ),
                onComment = {
                    onComment = true
                },
                onFavorite = {
                    onFavorite = true
                },
                onImage = {
                    onImage = true
                },
                onLike = {
                    onLike = true
                },
                onMenu = {
                    onMenu = true
                },
                onName = {
                    onName = true
                },
                onProfile = {
                    onProfile = true
                },
                onRestaurant = {
                    onRestaurant = true
                },
                onShare = {
                    onShare = true
                },
                isZooming = {
                    isZooming = true
                },
                imageLoadCompose = { modifier, _, _, _, _ ->
                    Image(
                        modifier = modifier,
                        painter = painterResource(id = R.drawable.default_profile_icon),
                        contentDescription = ""
                    )
                },
                onLikes = {
                    onLikes = true
                }
            )
        }
    }

    @Test
    fun displayTest() {
        composeRules.onNodeWithTag("txtName").assertTextEquals(userName)
        composeRules.onNodeWithTag("txtRestaurantName").assertTextEquals("restaurantName")
        //composeRules.onNodeWithTag("txtContents").assertTextContains(summarizedString(nickName = userName, text = contents, 20).text)
        composeRules.onNodeWithTag("txtDate").assertTextEquals(createdDate.formatedDate())
        composeRules.onNodeWithTag("txtLikes")
            .assertTextEquals(composeRules.activity.getString(R.string.like, likeAmount))
    }

    @Test
    fun clickEventTest() {
        assertFalse(onComment)
        composeRules.onNodeWithTag("btnComment").performClick()
        assertTrue(onComment)

        assertFalse(onFavorite)
        composeRules.onNodeWithTag("btnFavorite").performClick()
        assertTrue(onFavorite)

        assertFalse(onImage)
        composeRules.onNodeWithTag("imgReview").performClick()
        assertTrue(onImage)

        assertFalse(onLike)
        composeRules.onNodeWithTag("btnUnLike").performClick()
        assertTrue(onLike)

        assertFalse(onMenu)
        composeRules.onNodeWithTag("btnMenu").performClick()
        assertTrue(onMenu)

        assertFalse(onName)
        composeRules.onNodeWithTag("txtName").performClick()
        assertTrue(onName)

        assertFalse(onProfile)
        composeRules.onNodeWithTag("imgProfile").performClick()
        assertTrue(onProfile)

        assertFalse(onRestaurant)
        composeRules.onNodeWithTag("txtRestaurantName").performClick()
        assertTrue(onRestaurant)

        assertFalse(onShare)
        composeRules.onNodeWithTag("btnShare").performClick()
        assertTrue(onShare)

        assertFalse(onLikes)
        composeRules.onNodeWithTag("txtLikes").performClick()
        assertTrue(onLikes)
    }

}