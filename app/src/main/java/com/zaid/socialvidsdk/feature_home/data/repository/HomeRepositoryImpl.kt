package com.zaid.socialvidsdk.feature_home.data.repository

import com.zaid.socialvidsdk.feature_home.data.HomeApiService
import com.zaid.socialvidsdk.feature_home.data.model.response.SocialVidResponse
import com.zaid.socialvidsdk.feature_home.domain.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApiService: HomeApiService
) : HomeRepository {
    override suspend fun getAllVideos(): SocialVidResponse =
        homeApiService.getAllVideos()
}