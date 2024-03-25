package com.zaid.socialvidsdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zaid.socialvidsdk.social_vid_sdk_app.SocialVidSdkApp
import com.zaid.socialvidsdk.theme.SocialVidSdkTheme
import com.zaid.socialvidsdk.utils.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            SocialVidSdkTheme {
                // A surface container using the 'background' color from the theme
                SocialVidSdkApp(networkMonitor = networkMonitor)
            }
        }
    }
}