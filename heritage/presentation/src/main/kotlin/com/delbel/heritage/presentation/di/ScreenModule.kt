package com.delbel.heritage.presentation.di

import com.delbel.heritage.presentation.listing.ListingScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ScreenModule {

    @ContributesAndroidInjector
    internal abstract fun contributeListingScreen(): ListingScreen
}