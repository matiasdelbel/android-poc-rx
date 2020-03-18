package com.delbel.heritage.testapp.di

import android.app.Application
import com.delbel.common.work.di.WorkerModule
import com.delbel.dagger.rx.di.DaggerRxModule
import com.delbel.heritage.gateway.di.HeritageGatewayModule
import com.delbel.heritage.testapp.MainApplication
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DaggerRxModule::class, WorkerModule::class, HeritageGatewayModule::class])
interface MainComponent {

    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent
    }
}