package com.sarang.torang

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.assertValueEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sarang.torang.compose.feed.internal.components.FeedTop
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedTopTest {

    private var onProfile = false

    @get:Rule
    val composeRules = createAndroidComposeRule<ComponentActivity>()

    val userName = "userName123"

    val rating = 3.5f

    @Before
    fun init() {
        composeRules.setContent {
            FeedTop(
                userName = userName,
                profilePictureUrl = "1/2023-09-14/10_44_39_302.jpeg",
                restaurantName = "restaurantName",
                rating = rating,
                onProfile = { onProfile = true }
            )
        }
    }


    @Test
    fun clickEventTest(){
        composeRules.onNodeWithTag("txtUserName")
                    .assertTextEquals(userName)
        Assert.assertFalse(onProfile)
        composeRules.onNodeWithTag("imgProfile").performClick()
        Assert.assertTrue(onProfile)

        composeRules.onNodeWithTag("rbProfile")
                    .assert(
                        SemanticsMatcher("Rating is $rating") { node ->
                            val info = node.config[SemanticsProperties.ProgressBarRangeInfo]
                            info.current == rating
                        }
                    )
    }
}