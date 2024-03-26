package com.zaid.socialvidsdk.feature_home.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaid.socialvidsdk.feature_home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    private fun getAllVideos() = viewModelScope.launch {
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
                    loading = false, showAllVideoResponse = showVideoResponse, snackBarMessage = "Data Fetch Successfully"
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
}