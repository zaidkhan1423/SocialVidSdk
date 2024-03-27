package com.zaid.socialvidsdk.feature_home.presentation.home_screen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.zaid.socialvidsdk.feature_home.domain.HomeRepository
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.effect.AnimationEffect
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.effect.PlayerErrorEffect
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.effect.ResetAnimationEffect
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.effect.VideoEffect
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.model.toLocal
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.state.HomeUiState
import com.zaid.socialvidsdk.utils.AppIcons
import com.zaid.socialvidsdk.utils.toMediaItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        getAllVideos()
    }

    fun onMessageDisplayed() {
        _homeUiState.update {
            it.copy(
                snackBarMessage = null
            )
        }
    }

    private fun getAllVideos() = viewModelScope.launch(Dispatchers.IO) {
        _homeUiState.update {
            it.copy(
                loading = true,
                snackBarMessage = null
            )
        }

        try {
            val showVideoResponse = homeRepository.getAllVideos()
            Log.d("HomeVM", "Success $showVideoResponse")
            _homeUiState.update { uiState ->
                uiState.copy(
                    loading = false,
                    socialVidResponse = showVideoResponse.toLocal(),
                    snackBarMessage = "Data Fetch Successfully"
                )
            }
        } catch (e: Exception) {
            // Handle error
            _homeUiState.update { uiState ->
                Log.d("HomeVM", "Error ${e.message}")

                uiState.copy(
                    loading = false, snackBarMessage = e.message
                )
            }
        }
    }

    private val _effect = MutableSharedFlow<VideoEffect>()
    val effect = _effect.asSharedFlow()

    fun createPlayer(context: Context) {
        _homeUiState.update { state ->
            if (state.player == null) {
                state.copy(player = ExoPlayer.Builder(context).build().apply {
                    repeatMode = androidx.media3.common.Player.REPEAT_MODE_ONE
                    setMediaItems((state.socialVidResponse.msg).toMediaItems())
                    prepare()
                })
            } else
                state
        }
    }

    fun releasePlayer(isChangingConfigurations: Boolean) {
        if (isChangingConfigurations)
            return
        _homeUiState.update { state ->
            state.player?.release()
            state.copy(player = null)
        }
    }

    fun onPlayerError() {
        viewModelScope.launch(Dispatchers.Main) {
            homeUiState.value.player?.let { player ->
                _effect.emit(
                    PlayerErrorEffect(
                        message = player.playerError?.message.toString(),
                        code = player.playerError?.errorCode ?: -1
                    )
                )
            }
        }
    }

    fun onTappedScreen() {
        viewModelScope.launch(Dispatchers.Main) {
            _effect.emit(ResetAnimationEffect)
            homeUiState.value.player?.let { player ->
                val drawable = if (player.isPlaying) {
                    player.pause()
                    AppIcons.PauseIcon
                } else {
                    player.play()
                    AppIcons.PlayIcon
                }
                _effect.emit(AnimationEffect(drawable))
            }
        }
    }
}