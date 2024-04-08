package com.clement.newsapp.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.clement.newsapp.feature.headlines.navigation.HeadlineNavigation
import com.clement.newsapp.feature.headlines.navigation.headlinesGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController = rememberAnimatedNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    startDestination: String = HeadlineNavigation.TopNewsScreen
) {
    AnimatedNavHost(
        navController = navController, startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Start, animationSpec = tween())
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Start,
                animationSpec = tween(),
                targetOffset = { it / 2 })
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.End,
                animationSpec = spring(),
                initialOffset = { it / 3 })
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.End, animationSpec = spring())
        }
    ) {
        headlinesGraph(navController = navController)
    }
}