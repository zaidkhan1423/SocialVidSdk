package com.zaid.socialvidsdk.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.media3.common.MediaItem
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.model.MsgLocal

fun List<MsgLocal>.toMediaItems(): List<MediaItem> {
    return map {
        MediaItem.fromUri(it.video)
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}