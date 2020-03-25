package com.delbel.heritage.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.delbel.heritage.domain.HeritageCoordinate
import com.delbel.heritage.domain.HeritageDetail
import io.reactivex.Single

interface HeritageRepository {

    fun obtainAll(): LiveData<PagedList<HeritageDetail>>

    fun obtainBy(id: String): Single<HeritageCoordinate>
}