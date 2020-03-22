package com.delbel.heritage.testapp

import android.app.Application
import androidx.work.WorkerFactory
import com.delbel.dagger.work.ext.initializeWorkManager
import com.delbel.heritage.testapp.di.DaggerMainComponent
import com.delbel.heritage.testapp.di.MainComponent
import javax.inject.Inject

class MainApplication : Application() {

    private val mainComponent: MainComponent by lazy {
        DaggerMainComponent.builder().application(application = this).build()
    }

    @Inject
    lateinit var factory: WorkerFactory

    override fun onCreate() {
        super.onCreate()

        injectDependencies()
        initializeWorkManager(factory)
    }

    private fun injectDependencies() = mainComponent.inject(application = this)
}