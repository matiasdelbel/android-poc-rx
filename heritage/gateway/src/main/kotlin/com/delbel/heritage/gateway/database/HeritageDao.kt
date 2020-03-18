package com.delbel.heritage.gateway.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.delbel.heritage.domain.HeritageCoordinate
import com.delbel.heritage.domain.HeritageDetail
import com.delbel.heritage.gateway.model.HeritageDo
import io.reactivex.Completable
import io.reactivex.Single

@Dao
internal interface HeritageDao {

    @Query("SELECT id, name, shortInfo, image FROM heritage")
    fun obtainAll(): DataSource.Factory<Int, HeritageDetail>

    @Query("SELECT id, name, lat, lng FROM heritage WHERE id == :id")
    fun obtainBy(id: String): Single<HeritageCoordinate>

    @Insert(onConflict = REPLACE)
    fun insertAll(heritages: List<HeritageDo>): Completable
}