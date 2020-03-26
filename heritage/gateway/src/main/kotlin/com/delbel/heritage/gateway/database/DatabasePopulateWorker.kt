package com.delbel.heritage.gateway.database

import android.content.Context
import androidx.work.*
import com.delbel.dagger.work.ListenableWorkerFactory
import com.delbel.heritage.gateway.source.AssetsDataSource
import javax.inject.Inject
import javax.inject.Provider

internal class DatabasePopulateWorker(
    appContext: Context,
    params: WorkerParameters,
    private val dataSource: AssetsDataSource,
    private val dao: HeritageDao
) : RxWorker(appContext, params) {

    override fun createWork() = dataSource.obtainAll()
        .flatMapCompletable { heritages -> dao.insertAll(heritages) }
        .toSingle { Result.success() }
        .onErrorReturn { Result.retry() }

    class Factory @Inject constructor(
        private val dataSource: Provider<AssetsDataSource>,
        private val dao: Provider<HeritageDao>
    ) : ListenableWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker =
            DatabasePopulateWorker(appContext, params, dataSource.get(), dao.get())
    }
}

internal fun WorkManager.enqueuePopulateDataBaseWork() {
    val workRequest = OneTimeWorkRequest.Builder(DatabasePopulateWorker::class.java)
        .addTag(DatabasePopulateWorker::class.java.name)
        .build()

    enqueue(workRequest)
}