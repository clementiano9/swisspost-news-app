package com.clement.newsapp.feature.headlines

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.clement.domain.model.Article
import com.clement.domain.model.Source
import com.clement.domain.repo.fake.FakeNewsRepository
import com.clement.domain.usecase.GetHeadlinesUseCase
import com.clement.newsapp.feature.headlines.extensions.formatDate
import com.clement.newsapp.feature.headlines.healines.HeadlineViewState
import com.clement.newsapp.feature.headlines.healines.Headlines
import com.clement.newsapp.feature.headlines.healines.HeadlinesViewModel
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI test for checking the correct behaviour of the Headlines screen
 * Verifies that, when a specific ui state is set, the corresponding
 * composables and details are shown
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class HeadlinesScreenTest {

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
            Headlines(
                viewState = HeadlineViewState(articles = emptyList(), isLoading = true),
                onItemClick = {},
                onRefresh = {})
        }
        composeTestRule.onNodeWithContentDescription(headlinesLoading).assertExists()
    }

    @Test
    fun test_load_success() {
        composeTestRule.setContent {
            Headlines(
                viewState = HeadlineViewState(articles = articleList, isLoading = false),
                onItemClick = {},
                onRefresh = {})
        }
        val articleTitle = articleList.first().title
        val articleDate = articleList.first().title
        composeTestRule.onNodeWithText(articleTitle).assertExists()
        composeTestRule.onNodeWithText(articleDate.formatDate(composeTestRule.activity))
            .assertExists()
    }

}

private val articleList = listOf(
    Article(
        author = "BBC News",
        content = "Solar eclipse: Path of darkness - scroll every mile of totality",
        description = "If you are not lucky enough to live along the route in North America, you can still follow its journey here.",
        publishedAt = "2024-04-08T09:22:14Z",
        source = Source(id = "bbc-news", name = "BBC News"),
        title = "Solar eclipse: Path of darkness - scroll every mile of totality",
        url = "https://www.bbc.co.uk/news/world-us-canada-68726825",
        urlToImage = ""
    ),
    Article(
        author = "BBC News",
        content = "In March 2022 invading Russian troops occupied the Ukrainian town of Bucha outside Kyiv.",
        description = "Ukraine war: Bucha's wounds still raw two years on",
        publishedAt = "2024-04-08T010:22:14Z",
        source = Source(id = "sourceId", name = "source"),
        title = "Ukraine war: Bucha's wounds still raw two years on",
        url = "https://www.bbc.co.uk/news/world-europe-68755814",
        urlToImage = ""
    ),
)

