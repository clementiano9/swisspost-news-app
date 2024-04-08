package com.clement.newsapp.feature.headlines

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.clement.domain.model.Article
import com.clement.domain.model.Source
import com.clement.newsapp.feature.headlines.details.NewsDetails
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI test for checking the correct behaviour of the Details screen
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var headlinesLoading: String

    @Before
    fun setUp() {
        composeTestRule.activity.apply {
            headlinesLoading = getString(R.string.headlines_loading)
        }
    }

    @Test
    fun test_loading_state() {
        composeTestRule.setContent {
            NewsDetails(article = data) {
            }
        }
        composeTestRule.onNodeWithText(data.title).assertExists()
        composeTestRule.onNodeWithText(data.description).assertExists()
        composeTestRule.onNodeWithText(data.content).assertExists()
    }
}

private val data = Article(
    author = "BBC News",
    content = "Solar eclipse: Path of darkness - scroll every mile of totality",
    description = "If you are not lucky enough to live along the route in North America, you can still follow its journey here.",
    publishedAt = "2024-04-08T09:22:14Z",
    source = Source(id = "bbc-news", name = "BBC News"),
    title = "Solar eclipse",
    url = "https://www.bbc.co.uk/news/world-us-canada-68726825",
    urlToImage = ""
)