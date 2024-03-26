package com.zaid.socialvidsdk.social_vid_sdk_app.navigation_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SocialVidSdkNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        contentColor = Color.Unspecified,
        containerColor = SocialVidSdkNavigationDefaults.navigationContainerColor(),
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
fun RowScope.SocialVidSdkNavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = SocialVidSdkNavigationDefaults.navigationSelectedColor(),
            indicatorColor = Color.Transparent
        ),
        modifier = modifier
    )
}

object SocialVidSdkNavigationDefaults {

    @Composable
    fun navigationContainerColor() = Color.Black
    @Composable
    fun navigationSelectedColor() = Color.Transparent

}