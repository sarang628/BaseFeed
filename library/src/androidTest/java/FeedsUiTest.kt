import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedsUiTest {

    @get:Rule
    val composeRules = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test() {
        composeRules.setContent {
        }
    }
}