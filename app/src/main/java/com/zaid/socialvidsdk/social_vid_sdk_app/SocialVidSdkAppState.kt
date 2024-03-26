package com.zaid.socialvidsdk.social_vid_sdk_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zaid.socialvidsdk.navigations.Screen
import com.zaid.socialvidsdk.navigations.TopLevelDestination
import com.zaid.socialvidsdk.utils.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberSocialVidSdkAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): SocialVidSdkAppState {
    return remember(
        networkMonitor,
        coroutineScope,
        navController
    ) {
        SocialVidSdkAppState(
            networkMonitor,
            coroutineScope,
            navController
        )
    }
}

class SocialVidSdkAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope,
    val navController: NavHostController,
    ) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val shouldShowBottomBar: Boolean
        @Composable get() = when(currentDestination?.route) {
            Screen.HomeScreen.route -> true
            Screen.DiscoverScreen.route -> true
            Screen.CreateVideoScreen.route -> true
            Screen.NotificationScreen.route -> true
            Screen.ProfileScreen.route -> true
            else -> false
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

}