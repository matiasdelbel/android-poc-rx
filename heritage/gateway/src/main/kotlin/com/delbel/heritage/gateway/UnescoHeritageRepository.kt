package com.delbel.heritage.gateway

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.delbel.heritage.domain.HeritageDetail
import com.delbel.heritage.domain.repository.HeritageRepository
import com.delbel.heritage.gateway.database.HeritageDao
import javax.inject.Inject

class UnescoHeritageRepository @Inject internal constructor(private val dao: HeritageDao) : HeritageRepository {

    override fun obtainAll(): LiveData<PagedList<HeritageDetail>> = dao.obtainAll().toLiveData(pageSize = 20)

    override fun obtainBy(id: String) = dao.obtainBy(id)
}