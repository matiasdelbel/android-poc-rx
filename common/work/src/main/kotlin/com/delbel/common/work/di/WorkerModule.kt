package com.delbel.common.work.di

import androidx.work.WorkerFactory
import com.delbel.common.work.ApplicationWorkerFactory
import dagger.Binds
import dagger.Module

@Module
interface WorkerModule {

    @Binds
    fun bindWorkerFactory(factory: ApplicationWorkerFactory): WorkerFactory
}