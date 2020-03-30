package com.delbel.heritage.presentation.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.delbel.dagger.viewmodel.general.ViewModelKey
import com.delbel.dagger.viewmodel.general.di.ViewModelFactoryModule
import com.delbel.dagger.viewmodel.savedstate.ViewModelFactory
import com.delbel.heritage.presentation.listing.ListingViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
internal interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListingViewModel::class)
    fun bindListingViewModel(viewModel: ListingViewModel): ViewModel
}

@AssistedModule
@Module(includes = [AssistedInject_AssistedViewModelModule::class])
internal interface AssistedViewModelModule

// TODO workaround for: https://github.com/square/AssistedInject/issues/81
internal interface AssistedViewModelFactory<T : ViewModel> : ViewModelFactory<T> {

    override fun create(handle: SavedStateHandle): T
}