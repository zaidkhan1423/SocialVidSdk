package com.zaid.socialvidsdk.feature_home.presentation.home_screen.model

import com.zaid.socialvidsdk.feature_home.data.model.response.AudioPath
import com.zaid.socialvidsdk.feature_home.data.model.response.Count
import com.zaid.socialvidsdk.feature_home.data.model.response.Msg
import com.zaid.socialvidsdk.feature_home.data.model.response.SocialVidResponse
import com.zaid.socialvidsdk.feature_home.data.model.response.Sound
import com.zaid.socialvidsdk.feature_home.data.model.response.UserInfo

data class SocialVidResponseLocal(
    val code: String,
    val msg: List<MsgLocal>,
    val s: String
)

data class MsgLocal(
    val __v: Int,
    val _id: String,
    val city: String,
    val count: CountLocal,
    val country: String,
    val created: String,
    val description: String,
    val fb_id: String,
    val gif: String,
    val id: Int,
    val is_block: Int,
    val liked: Int,
    val score: Int,
    val sound: SoundLocal,
    val status: String,
    val thum: String,
    val tp: Int,
    val uid: String,
    val user_info: UserInfoLocal,
    val video: String
)

data class CountLocal(
    val _id: String,
    val like_count: Int,
    val video_comment_count: Int,
    val view: Int
)

data class SoundLocal(
    val _id: String,
    val audio_path: AudioPathLocal,
    val created: String,
    val description: String,
    val id: Int,
    val section: String,
    val sound_name: String,
    val thum: String
)

data class UserInfoLocal(
    val _id: String,
    val fb_id: String,
    val first_name: String,
    val gender: String,
    val last_name: String,
    val profile_pic: String,
    val username: String,
    val verified: String
)

data class AudioPathLocal(
    val acc: String,
    val mp3: String
)

fun SocialVidResponse.toLocal(): SocialVidResponseLocal = SocialVidResponseLocal(
    code = code,
    msg = msg.toLocalMsgs(),
    s = s
)
fun List<Msg>.toLocalMsgs() = map(Msg::toLocal)
fun Msg.toLocal(): MsgLocal = MsgLocal(
    __v = __v,
    _id = _id,
    city = city,
    count = count.toLocal(),
    country = country,
    created = created,
    description = description,
    fb_id = fb_id,
    gif = gif,
    id = id,
    is_block = is_block,
    liked = liked,
    score = score,
    sound = sound.toLocal(),
    status = status,
    thum = thum,
    tp = tp,
    uid = uid,
    user_info = user_info.toLocal(),
    video = video
)

fun Count.toLocal(): CountLocal = CountLocal(
    _id = _id,
    like_count = like_count,
    video_comment_count = video_comment_count,
    view = view
)

fun Sound.toLocal(): SoundLocal = SoundLocal(
    _id = _id,
    audio_path = audio_path.toLocal(),
    created = created,
    description = description,
    id = id,
    section = section,
    sound_name = sound_name,
    thum = thum
)

fun UserInfo.toLocal(): UserInfoLocal = UserInfoLocal(
    _id = _id,
    fb_id = fb_id,
    first_name = first_name,
    gender = gender,
    last_name = last_name,
    profile_pic = profile_pic,
    username = username,
    verified = verified
)

fun AudioPath.toLocal(): AudioPathLocal = AudioPathLocal(
    acc = acc,
    mp3 = mp3
)