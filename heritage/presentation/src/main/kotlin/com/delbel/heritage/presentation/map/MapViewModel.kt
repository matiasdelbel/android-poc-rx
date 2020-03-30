package com.delbel.heritage.presentation.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.delbel.dagger.rx.ComputationScheduler
import com.delbel.heritage.domain.repository.HeritageRepository
import com.delbel.heritage.presentation.di.AssistedViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.Scheduler

internal class MapViewModel @AssistedInject constructor(
    repository: HeritageRepository,
    @ComputationScheduler computationScheduler: Scheduler,
    @Assisted handle: SavedStateHandle
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<MapViewModel>

    val coordinates =
        repository.obtainBy(id = handle["heritage_id"]!!)
            .subscribeOn(computationScheduler)
            .toFlowable()
            .toLiveData()
}