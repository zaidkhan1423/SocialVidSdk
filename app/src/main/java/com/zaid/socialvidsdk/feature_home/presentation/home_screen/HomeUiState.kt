package com.zaid.socialvidsdk.feature_home.presentation.home_screen

import com.zaid.socialvidsdk.feature_home.data.model.response.AudioPath
import com.zaid.socialvidsdk.feature_home.data.model.response.Count
import com.zaid.socialvidsdk.feature_home.data.model.response.Msg
import com.zaid.socialvidsdk.feature_home.data.model.response.ShowAllVideoResponse
import com.zaid.socialvidsdk.feature_home.data.model.response.Sound
import com.zaid.socialvidsdk.feature_home.data.model.response.UserInfo

data class HomeUiState(
    val loading: Boolean = false,
    val shouldNavigate: Boolean = false,
    val snackBarMessage: String? = null,
    val showAllVideoResponse: ShowAllVideoResponse = ShowAllVideoResponse(
        code = "", msg = listOf(
            Msg(
                __v = 0,
                _id = "",
                city = "",
                count = Count(
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
                sound = Sound(
                    _id = "",
                    audio_path = AudioPath(
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
                user_info = UserInfo(
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
)