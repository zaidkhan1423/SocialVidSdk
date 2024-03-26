package com.zaid.socialvidsdk.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zaid.socialvidsdk.navigations.nav_graphs.topLevelGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = NavGraphRoutes.TOP_LEVEL) {
        topLevelGraph(navController = navHostController)
    }
}

object NavGraphRoutes {
    const val TOP_LEVEL = "top_level_routes"
}