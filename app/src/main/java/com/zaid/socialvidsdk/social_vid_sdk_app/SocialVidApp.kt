package com.zaid.socialvidsdk.social_vid_sdk_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import com.zaid.socialvidsdk.R
import com.zaid.socialvidsdk.navigations.AppNavHost
import com.zaid.socialvidsdk.navigations.Screen
import com.zaid.socialvidsdk.navigations.TopLevelDestination
import com.zaid.socialvidsdk.social_vid_sdk_app.navigation_bar.SocialVidSdkNavigationBar
import com.zaid.socialvidsdk.social_vid_sdk_app.navigation_bar.SocialVidSdkNavigationItem
import com.zaid.socialvidsdk.utils.Icon
import com.zaid.socialvidsdk.utils.NetworkMonitor


@Composable
fun SocialVidSdkApp(
    networkMonitor: NetworkMonitor,
    appState: SocialVidSdkAppState = rememberSocialVidSdkAppState(
        networkMonitor = networkMonitor
    )
) {

    val snackBarHostState = remember { SnackbarHostState() }

    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    // If user is not connected to the internet show a snack bar to inform them.
    val notConnectedMessage = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackBarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                SocialVidSdkBottomBar(
                    currentDestination = appState.currentDestination,
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = { topLevelDestination ->
                        appState.navController.navigate(topLevelDestination.route) {
                            if (topLevelDestination.route == Screen.HomeScreen.route) {
                                popUpTo(0)
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->

        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AppNavHost(
                navHostController = appState.navController,
                onShowSnackBar = { message, action, duration ->
                    snackBarHostState.showSnackbar(
                        message = message,
                        actionLabel = action,
                        duration = duration,
                        withDismissAction = duration == SnackbarDuration.Indefinite
                    ) == SnackbarResult.ActionPerformed
                }
            )
        }
    }
}


@Composable
private fun SocialVidSdkBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {

    SocialVidSdkNavigationBar(
        modifier = modifier.height(54.dp)
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination?.route == destination.route

            SocialVidSdkNavigationItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        is Icon.ImageVectorIcon -> Image(
                            imageVector = icon.imageVector,
                            contentDescription = null,
                        )

                        is Icon.DrawableResourceIcon -> Image(
                            painter = painterResource(id = icon.id),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    }
}