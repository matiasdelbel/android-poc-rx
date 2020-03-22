package com.delbel.heritage.gateway.di

import com.delbel.dagger.work.ListenableWorkerFactory
import com.delbel.dagger.work.di.WorkerKey
import com.delbel.heritage.gateway.database.DatabasePopulateWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface WorkerBindingModule {

    @Binds
    @IntoMap
    @WorkerKey(DatabasePopulateWorker::class)
    fun bindDatabasePopulateWorker(factory: DatabasePopulateWorker.Factory): ListenableWorkerFactory
}