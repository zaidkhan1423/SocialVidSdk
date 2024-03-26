package com.zaid.socialvidsdk.feature_home.data.model.response

data class Sound(
    val _id: String,
    val audio_path: AudioPath,
    val created: String,
    val description: String,
    val id: Int,
    val section: String,
    val sound_name: String,
    val thum: String
)