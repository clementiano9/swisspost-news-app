package com.clement.newsapp.navigation

import androidx.annotation.StringRes
import com.clement.newsapp.R

sealed class Screen(@StringRes val title: Int, val route: String) {
    object MainScreen: Screen(R.string.nav_main, "main-screen")
    object NewsDetailsScreen: Screen(R.string.nav_news_details, "news-details-screen")

}