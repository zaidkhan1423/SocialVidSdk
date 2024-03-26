package com.zaid.socialvidsdk.navigations

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object DiscoverScreen : Screen("discover_screen")
    data object CreateVideoScreen : Screen("create_video_screen")
    data object NotificationScreen : Screen("notification_screen")
    data object ProfileScreen : Screen("profile_screen")
}