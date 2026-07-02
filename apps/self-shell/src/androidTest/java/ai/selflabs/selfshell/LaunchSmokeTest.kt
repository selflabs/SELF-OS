package ai.selflabs.selfshell

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
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
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            composeTestRule
                .onAllNodesWithText("SELF OS Personal Intelligence (Community Edition)", useUnmergedTree = true)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule
            .onNodeWithText("SELF OS Personal Intelligence (Community Edition)")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Welcome to your local personal intelligence shell.")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
    }
}
