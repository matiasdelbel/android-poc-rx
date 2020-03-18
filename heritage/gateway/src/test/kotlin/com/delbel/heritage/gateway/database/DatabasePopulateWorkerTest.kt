package com.delbel.heritage.gateway.database

import androidx.work.ListenableWorker.Result.Retry
import androidx.work.ListenableWorker.Result.Success
import com.delbel.heritage.gateway.model.HeritageDo
import com.delbel.heritage.gateway.source.AssetsDataSource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test

class DatabasePopulateWorkerTest {

    @Test
    fun `createWork should return success`() {
        val heritagesToInsert = listOf<HeritageDo>(mock(), mock())
        val heritagesSingle = Single.create<List<HeritageDo>> { emitter ->
            emitter.onSuccess(heritagesToInsert)
        }
        val dataSource = mock<AssetsDataSource> { on { obtainAll() } doReturn heritagesSingle }
        val insertCompletable = Completable.create { emitter -> emitter.onComplete() }
        val dao = mock<HeritageDao> {
            on { insertAll(heritagesToInsert) } doReturn insertCompletable
        }
        val worker = DatabasePopulateWorker(mock(), mock(), dataSource, dao)

        val testObserver = worker.createWork().test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValue { it is Success }
    }

    @Test
    fun `createWork with obtain all error should return try`() {
        val heritagesSingle = Single.create<List<HeritageDo>> { emitter -> emitter.onError(mock()) }
        val dataSource = mock<AssetsDataSource> { on { obtainAll() } doReturn heritagesSingle }
        val worker = DatabasePopulateWorker(mock(), mock(), dataSource, mock())

        val testObserver = worker.createWork().test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValue { it is Retry }
    }

    @Test
    fun `createWork with insert all error should return try`() {
        val heritagesToInsert = listOf<HeritageDo>(mock(), mock())
        val heritagesSingle = Single.create<List<HeritageDo>> { emitter ->
            emitter.onSuccess(heritagesToInsert)
        }
        val dataSource = mock<AssetsDataSource> { on { obtainAll() } doReturn heritagesSingle }
        val insertCompletable = Completable.create { emitter -> emitter.onError(mock()) }
        val dao = mock<HeritageDao> {
            on { insertAll(heritagesToInsert) } doReturn insertCompletable
        }
        val worker = DatabasePopulateWorker(mock(), mock(), dataSource, dao)

        val testObserver = worker.createWork().test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValue { it is Retry }
    }
}