package com.clement.newsapp.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.clement.newsapp.ui.details.NewsDetails
import com.clement.newsapp.extensions.toArticle
import com.clement.newsapp.extensions.toBundle
import com.clement.newsapp.ui.healines.Headlines
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController = rememberAnimatedNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    startDestination: String = Screen.MainScreen.route
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
        composable(
            Screen.MainScreen.route
        ) {
            Headlines() {
                val destination = navController.graph.findNode(Screen.NewsDetailsScreen.route)
                if (destination != null) {
                    navController.navigate(destination.id, it.toBundle())
                }
            }
        }
        composable(
            route = Screen.NewsDetailsScreen.route,

            ) {
            NewsDetails(
                it.arguments?.toArticle(),
                onBackPress = { navController.popBackStack() }
            )
        }
    }
}