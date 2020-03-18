package com.delbel.heritage.gateway.di

import android.app.Application
import android.content.res.AssetManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
internal class AssetsDataSourceModule {

    @Provides
    fun provideAssetManager(application: Application): AssetManager = application.assets

    @Provides
    @Reusable
    fun provideJsonParser() = Gson()
}