package com.delbel.heritage.testapp

import android.app.Application
import androidx.work.WorkerFactory
import com.delbel.dagger.work.ext.initializeWorkManager
import com.delbel.heritage.testapp.di.DaggerMainComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var factory: WorkerFactory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        injectDependencies()

        initializeWorkManager(factory)
        initializeImageLoader()
    }

    override fun androidInjector() = androidInjector

    private fun injectDependencies() = DaggerMainComponent.builder()
        .application(application = this)
        .build()
        .inject(application = this)

    private fun initializeImageLoader() = Fresco.initialize(this)
}