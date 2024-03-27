package com.zaid.socialvidsdk.feature_home.domain

import com.zaid.socialvidsdk.feature_home.data.model.response.SocialVidResponse

interface HomeRepository {

    suspend fun getAllVideos(): SocialVidResponse

}