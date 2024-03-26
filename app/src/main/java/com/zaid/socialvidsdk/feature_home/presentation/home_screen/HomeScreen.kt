package com.zaid.socialvidsdk.feature_home.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onMessageDisplay: () -> Unit,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean
) {
    LaunchedEffect(key1 = homeUiState) {
        if (homeUiState.snackBarMessage != null) {
            onShowSnackBar(homeUiState.snackBarMessage, null, SnackbarDuration.Short)
            onMessageDisplay()
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home Screen", fontSize = 28.sp)
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        homeUiState = HomeUiState(),
        onMessageDisplay = {},
        onShowSnackBar = { _, _, _ -> false }
    )
}