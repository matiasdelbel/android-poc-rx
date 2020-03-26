package com.delbel.heritage.gateway

import androidx.paging.DataSource
import com.delbel.heritage.domain.HeritageCoordinate
import com.delbel.heritage.domain.HeritageDetail
import com.delbel.heritage.gateway.database.HeritageDao
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Test

class UnescoHeritageRepositoryTest {

    @Test
    fun `obtainAll should return live data with results`() {
        val daoResult = mock<DataSource.Factory<Int, HeritageDetail>>()
        val dao = mock<HeritageDao> { on { obtainAll() } doReturn daoResult }
        val repository = UnescoHeritageRepository(dao)

        val result = repository.obtainAll()

        verify(dao).obtainAll()
        assertThat(result).isInstanceOf(Flowable::class.java)
    }

    @Test
    fun `obtainBy know id for dao should emit it`() {
        val expectedResult = mock<HeritageCoordinate>()
        val daoResult = Single.create<HeritageCoordinate> { emitter ->
            emitter.onSuccess(expectedResult)
        }
        val dao = mock<HeritageDao> { on { obtainBy("id") } doReturn daoResult }
        val repository = UnescoHeritageRepository(dao)

        val testObserver = repository.obtainBy("id").test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValue { it == expectedResult }
    }

    @Test
    fun `obtainBy with error should emit error`() {
        val expectedError = mock<Throwable>()
        val daoResult = Single.create<HeritageCoordinate> { emitter ->
            emitter.onError(expectedError)
        }
        val dao = mock<HeritageDao> { on { obtainBy("id") } doReturn daoResult }
        val repository = UnescoHeritageRepository(dao)

        val testObserver = repository.obtainBy("id").test()

        testObserver.assertNoValues()
        testObserver.assertNotComplete()
        testObserver.assertError { it == expectedError }
    }
}