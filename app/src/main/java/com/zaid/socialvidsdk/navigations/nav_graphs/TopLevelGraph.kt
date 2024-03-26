package com.zaid.socialvidsdk.navigations.nav_graphs

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.zaid.socialvidsdk.feature_Create_Video.CreateVideoScreen
import com.zaid.socialvidsdk.feature_discover.DiscoverScreen
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.HomeScreen
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.HomeScreenViewModel
import com.zaid.socialvidsdk.feature_notification.NotificationScreen
import com.zaid.socialvidsdk.feature_profile.ProfileScreen
import com.zaid.socialvidsdk.navigations.NavGraphRoutes
import com.zaid.socialvidsdk.navigations.Screen

fun NavGraphBuilder.topLevelGraph(
    navController: NavController,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean
) {
    navigation(startDestination = Screen.HomeScreen.route, route = NavGraphRoutes.TOP_LEVEL) {

        composable(route = Screen.HomeScreen.route) {

            val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
            val homeScreenUiState by homeScreenViewModel.homeUiState.collectAsStateWithLifecycle()

            HomeScreen(
                homeUiState = homeScreenUiState,
                onShowSnackBar = onShowSnackBar,
                onMessageDisplay = { homeScreenViewModel.onMessageDisplayed() },
            )
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