package ai.selflabs.selfshell

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import ai.selflabs.selfshell.ui.UiTestTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class LaunchSmokeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun launchesHomeScreen() {
        composeTestRule.waitUntil(timeoutMillis = 15_000) {
            composeTestRule
                .onAllNodesWithTag(UiTestTags.HOME_SCREEN, useUnmergedTree = true)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onNodeWithTag(UiTestTags.HOME_SCREEN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(UiTestTags.HOME_WELCOME).assertIsDisplayed()
        composeTestRule.onNodeWithTag(UiTestTags.TAB_HOME).assertIsDisplayed()
    }
}
