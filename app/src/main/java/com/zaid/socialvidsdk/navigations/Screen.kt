package com.zaid.socialvidsdk.navigations

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
}