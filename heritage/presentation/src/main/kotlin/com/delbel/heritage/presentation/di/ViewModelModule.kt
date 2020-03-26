package com.delbel.heritage.presentation.di

import androidx.lifecycle.ViewModel
import com.delbel.dagger.viewmodel.di.DaggerViewModelFactoryModule
import com.delbel.dagger.viewmodel.di.ViewModelKey
import com.delbel.heritage.presentation.listing.ListingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [DaggerViewModelFactoryModule::class])
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListingViewModel::class)
    abstract fun bindListingViewModel(viewModel: ListingViewModel): ViewModel
}