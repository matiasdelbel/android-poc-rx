package com.delbel.heritage.gateway.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.delbel.heritage.gateway.database.HeritageDatabase
import dagger.Module
import dagger.Provides

@Module
internal class DatabaseModule {

    companion object {
        private const val DATABASE_NAME = "heritages.db"
    }

    @Provides
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, HeritageDatabase::class.java, DATABASE_NAME)
            .addCallback(object : RoomDatabase.Callback() {

                override fun onCreate(db: SupportSQLiteDatabase) {
                    TODO("Populate the data base")
                }
            })
            .build()

    @Provides
    fun provideHeritageDao(database: HeritageDatabase) = database.heritageDao()
}