package com.zaid.socialvidsdk.feature_home.data

import com.zaid.socialvidsdk.feature_home.data.model.response.ShowAllVideoResponse
import com.zaid.socialvidsdk.utils.EndPoints.INDEX_PHP
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {

    @GET(INDEX_PHP)
    suspend fun getAllVideos(@Query("p") name: String = "showAllVideos"): ShowAllVideoResponse
}