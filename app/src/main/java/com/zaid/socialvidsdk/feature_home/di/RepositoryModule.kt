package com.zaid.socialvidsdk.feature_home.di

import com.zaid.socialvidsdk.feature_home.data.repository.HomeRepositoryImpl
import com.zaid.socialvidsdk.feature_home.domain.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

}