package com.zaid.socialvidsdk.navigations

import com.zaid.socialvidsdk.utils.AppIcons
import com.zaid.socialvidsdk.utils.Icon


enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val route: String
) {

    HOME(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeSelected),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeUnselected),
        route = Screen.HomeScreen.route
    ),
    DISCOVER(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.DiscoverSelected),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.DiscoverUnselected),
        route = Screen.DiscoverScreen.route
    ),
    CREATE(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.CreateVideoIcon),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.CreateVideoIcon),
        route = Screen.CreateVideoScreen.route
    ),
    NOTIFICATIONS(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.NotificationSelected),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.NotificationUnselected),
        route = Screen.NotificationScreen.route
    ),
    PROFILE(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.ProfileSelected),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.ProfileUnselected),
        route = Screen.ProfileScreen.route
    )

}