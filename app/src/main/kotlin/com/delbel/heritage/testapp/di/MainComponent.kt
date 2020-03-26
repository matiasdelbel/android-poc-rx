package com.delbel.heritage.testapp.di

import android.app.Application
import com.delbel.dagger.rx.di.DaggerRxModule
import com.delbel.dagger.work.di.DaggerWorkerModule
import com.delbel.heritage.gateway.di.HeritageGatewayModule
import com.delbel.heritage.presentation.di.PresentationModule
import com.delbel.heritage.testapp.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DaggerRxModule::class,
        DaggerWorkerModule::class,
        PresentationModule::class,
        HeritageGatewayModule::class]
)
@Singleton
internal interface MainComponent {

    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent
    }
}