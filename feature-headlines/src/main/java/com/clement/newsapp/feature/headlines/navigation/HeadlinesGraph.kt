package com.clement.newsapp.feature.headlines.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.clement.newsapp.feature.headlines.details.NewsDetails
import com.clement.newsapp.feature.headlines.extensions.toArticle
import com.clement.newsapp.feature.headlines.extensions.toBundle
import com.clement.newsapp.feature.headlines.healines.Headlines
import com.clement.newsapp.feature.headlines.healines.HeadlinesScreen

object HeadlineNavigation {
    const val MainScreen = "main-screen"
    const val TopNewsScreen = "top-news-screen"
    const val NewsDetailsScreen = "news-details-screen"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.headlinesGraph(navController: NavHostController) {
    composable(
        HeadlineNavigation.TopNewsScreen
    ) {
        HeadlinesScreen() {
            val destination = navController.graph.findNode(HeadlineNavigation.NewsDetailsScreen)
            if (destination != null) {
                navController.navigate(destination.id, it.toBundle())
            }
        }
    }
    composable(route = HeadlineNavigation.NewsDetailsScreen) {
        NewsDetails(
            it.arguments?.toArticle(),
            onBackPress = { navController.popBackStack() }
        )
    }
}