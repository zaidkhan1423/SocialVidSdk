package com.zaid.socialvidsdk.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.zaid.socialvidsdk.R

object AppIcons {
    val HomeSelected = R.drawable.selected_home
    val HomeUnselected = R.drawable.unselected_home
    val ProfileSelected = R.drawable.selected_profile
    val ProfileUnselected = R.drawable.unselected_profile
    val DiscoverSelected = R.drawable.selected_discover
    val DiscoverUnselected = R.drawable.unselected_discover
    val CreateVideoIcon = R.drawable.button_shape
    val NotificationSelected = R.drawable.selected_notification
    val NotificationUnselected = R.drawable.unselected_notification
    val HeartIcon  = R.drawable.heart_icon
    val CommentIcon = R.drawable.comment_icon
    val ShareIcon = R.drawable.share_icon
    val PauseIcon = R.drawable.pause
    val PlayIcon = R.drawable.play
    val MusicIcon = R.drawable.music_icon
    val MenuIcon = R.drawable.menu_icon
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}