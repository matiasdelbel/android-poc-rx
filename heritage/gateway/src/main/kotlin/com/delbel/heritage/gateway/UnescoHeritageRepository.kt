package com.delbel.heritage.gateway

import androidx.paging.PagedList
import androidx.paging.toFlowable
import com.delbel.heritage.domain.repository.HeritageRepository
import com.delbel.heritage.gateway.database.HeritageDao
import javax.inject.Inject

class UnescoHeritageRepository @Inject internal constructor(private val dao: HeritageDao) : HeritageRepository {

    companion object {
        private val pageConfig = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(40)
            .setEnablePlaceholders(false)
            .build()
    }

    override fun obtainAll() = dao.obtainAll().toFlowable(config = pageConfig)

    override fun obtainBy(id: String) = dao.obtainBy(id)
}