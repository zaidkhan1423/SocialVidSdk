package com.zaid.socialvidsdk.navigations

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zaid.socialvidsdk.navigations.nav_graphs.topLevelGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean,
) {
    NavHost(navController = navHostController, startDestination = NavGraphRoutes.TOP_LEVEL) {
        topLevelGraph(navController = navHostController, onShowSnackBar = onShowSnackBar)
    }
}

object NavGraphRoutes {
    const val TOP_LEVEL = "top_level_routes"
}