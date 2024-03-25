package com.zaid.socialvidsdk.navigations.nav_graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zaid.socialvidsdk.navigations.mainGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = NavGraphRoutes.MAIN) {
        mainGraph(navController = navHostController)
    }
}

object NavGraphRoutes {
    const val MAIN = "main_rought"
}