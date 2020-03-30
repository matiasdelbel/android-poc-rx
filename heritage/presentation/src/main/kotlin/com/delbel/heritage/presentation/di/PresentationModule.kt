package com.delbel.heritage.presentation.di

import dagger.Module

@Module(includes = [ViewModelModule::class, AssistedViewModelModule::class, ScreenModule::class])
interface PresentationModule