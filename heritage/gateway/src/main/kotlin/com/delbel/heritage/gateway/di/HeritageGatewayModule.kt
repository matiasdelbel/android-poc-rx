package com.delbel.heritage.gateway.di

import dagger.Module

@Module(includes = [AssetsDataSourceModule::class, DatabaseModule::class])
class HeritageGatewayModule