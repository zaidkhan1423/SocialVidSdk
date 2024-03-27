package com.zaid.socialvidsdk.feature_home.presentation.home_screen.state

import androidx.media3.common.Player
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.model.AudioPathLocal
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.model.CountLocal
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.model.MsgLocal
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.model.SocialVidResponseLocal
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.model.SoundLocal
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.model.UserInfoLocal

data class HomeUiState(
    val player: Player? = null,
    val loading: Boolean = false,
    val shouldNavigate: Boolean = false,
    val snackBarMessage: String? = null,
    val socialVidResponse: SocialVidResponseLocal = SocialVidResponseLocal(
        code = "", msg = listOf(
            MsgLocal(
                __v = 0,
                _id = "",
                city = "",
                count = CountLocal(
                    _id = "",
                    like_count = 0,
                    video_comment_count = 0,
                    view = 0
                ),
                country = "",
                created = "",
                description = "",
                fb_id = "",
                gif = "",
                id = 0,
                is_block = 0,
                liked = 0,
                score = 0,
                sound = SoundLocal(
                    _id = "",
                    audio_path = AudioPathLocal(
                        acc = "",
                        mp3 = ""
                    ),
                    created = "",
                    description = "",
                    id = 0,
                    section = "",
                    sound_name = "",
                    thum = ""
                    ),
                status = "",
                thum = "",
                tp = 0,
                uid = "",
                user_info = UserInfoLocal(
                    _id = "",
                    fb_id = "",
                    first_name = "",
                    gender = "",
                    last_name = "",
                    profile_pic = "",
                    username = "",
                    verified = ""
                ),
                video = ""
            )
        ), s = ""
    )
){
    fun playMediaAt(position: Int) {
        player?.let { player ->
            if (player.currentMediaItemIndex == position && player.isPlaying)
                return
            player.seekToDefaultPosition(position)
            player.playWhenReady = true
            player.prepare()
        }
    }
}