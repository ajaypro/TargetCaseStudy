package com.target.targetcasestudy.di

import android.content.Context
import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.data.network.NetworkService
import com.target.targetcasestudy.data.network.Networking
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkService(@ApplicationContext application: Context): NetworkService =

        Networking.create(BuildConfig.BASE_URL, application.cacheDir, 10 * 1024 * 1024)

}