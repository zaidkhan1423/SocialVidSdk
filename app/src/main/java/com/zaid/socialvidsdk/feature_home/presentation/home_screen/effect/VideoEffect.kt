package com.zaid.socialvidsdk.feature_home.presentation.home_screen.effect

import androidx.annotation.DrawableRes

sealed class VideoEffect
data class PlayerErrorEffect(val message: String, val code: Int): VideoEffect()
data class AnimationEffect(@DrawableRes val drawable: Int): VideoEffect()
object ResetAnimationEffect: VideoEffect()