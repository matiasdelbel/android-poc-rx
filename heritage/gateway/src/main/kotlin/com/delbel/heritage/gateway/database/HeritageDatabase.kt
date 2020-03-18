package com.delbel.heritage.gateway.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delbel.heritage.gateway.model.HeritageDo

@Database(entities = [HeritageDo::class], version = 1, exportSchema = false)
internal abstract class HeritageDatabase : RoomDatabase() {

    abstract fun heritageDao(): HeritageDao
}