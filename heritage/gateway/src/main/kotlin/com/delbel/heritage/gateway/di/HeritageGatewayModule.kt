package com.delbel.heritage.gateway.di

import com.delbel.heritage.domain.repository.HeritageRepository
import com.delbel.heritage.gateway.UnescoHeritageRepository
import dagger.Binds
import dagger.Module

@Module(includes = [AssetsDataSourceModule::class, DatabaseModule::class])
interface HeritageGatewayModule {

    @Binds
    fun bindRepository(repository: UnescoHeritageRepository): HeritageRepository
}