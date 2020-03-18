package com.delbel.common.rx.di

import com.delbel.common.rx.ComputationScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers

@Module
class RxModule {

    @Provides
    @ComputationScheduler
    fun provideComputationScheduler() = Schedulers.computation()
}