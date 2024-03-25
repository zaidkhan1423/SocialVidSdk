package com.zaid.socialvidsdk.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.zaid.socialvidsdk.feature_home.HomeScreen
import com.zaid.socialvidsdk.navigations.nav_graphs.NavGraphRoutes

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = Screen.HomeScreen.route, route = NavGraphRoutes.MAIN) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

    }
}