package com.zaid.socialvidsdk.navigations.nav_graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.zaid.socialvidsdk.feature_Create_Video.CreateVideoScreen
import com.zaid.socialvidsdk.feature_discover.DiscoverScreen
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.HomeScreen
import com.zaid.socialvidsdk.feature_notification.NotificationScreen
import com.zaid.socialvidsdk.feature_profile.ProfileScreen
import com.zaid.socialvidsdk.navigations.NavGraphRoutes
import com.zaid.socialvidsdk.navigations.Screen

fun NavGraphBuilder.topLevelGraph(
    navController: NavController
) {
    navigation(startDestination = Screen.HomeScreen.route, route = NavGraphRoutes.TOP_LEVEL) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.DiscoverScreen.route) {
            DiscoverScreen()
        }

        composable(route = Screen.CreateVideoScreen.route) {
            CreateVideoScreen()
        }

        composable(route = Screen.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}