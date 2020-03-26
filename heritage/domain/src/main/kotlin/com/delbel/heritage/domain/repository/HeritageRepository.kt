package com.delbel.heritage.domain.repository

import androidx.paging.PagedList
import com.delbel.heritage.domain.HeritageCoordinate
import com.delbel.heritage.domain.HeritageDetail
import io.reactivex.Flowable
import io.reactivex.Single

interface HeritageRepository {

    fun obtainAll(): Flowable<PagedList<HeritageDetail>>

    fun obtainBy(id: String): Single<HeritageCoordinate>
}