package com.delbel.heritage.gateway.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.WorkManager
import com.delbel.heritage.gateway.database.DatabasePopulateWorker
import com.delbel.heritage.gateway.database.HeritageDatabase
import dagger.Module
import dagger.Provides

@Module (includes = [WorkerBindingModule::class])
internal class DatabaseModule {

    companion object {
        private const val DATABASE_NAME = "heritages.db"
    }

    @Provides
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, HeritageDatabase::class.java, DATABASE_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    WorkManager.getInstance().enqueue(DatabasePopulateWorker.workRequest())
                }
            })
            .build()

    @Provides
    fun provideHeritageDao(database: HeritageDatabase) = database.heritageDao()
}