package com.delbel.heritage.presentation.di

import com.delbel.heritage.presentation.listing.ListingScreen
import com.delbel.heritage.presentation.map.MapScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ScreenModule {

    @ContributesAndroidInjector
    fun contributeListingScreen(): ListingScreen

    @ContributesAndroidInjector
    fun contributeMapScreen(): MapScreen
}