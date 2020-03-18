package com.delbel.heritage.gateway.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.platform.app.InstrumentationRegistry
import com.delbel.heritage.gateway.mock.domain.HeritageCoordinateMock
import com.delbel.heritage.gateway.mock.domain.HeritageDetailMock
import com.delbel.heritage.gateway.mock.gateway.HeritageDoMock
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeritageDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: HeritageDatabase
    private lateinit var heritageDao: HeritageDao

    @Before
    fun before() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room
            .inMemoryDatabaseBuilder<HeritageDatabase>(context, HeritageDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        heritageDao = database.heritageDao()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun obtainBy_withAachenCathedralId_shouldReadItFromDatabase() {
        heritageDao.insertAll(heritages = listOf(HeritageDoMock.AACHEN_CATHEDRAL)).blockingAwait()

        val testObserver = heritageDao.obtainBy(id = HeritageDoMock.AACHEN_CATHEDRAL.id).test()

        testObserver.assertValue { it == HeritageCoordinateMock.AACHEN_CATHEDRAL }
    }

    @Test
    fun obtainAll_withAachenCathedralId_shouldReturnOnePageWithAachenCathedral() {
        heritageDao.insertAll(heritages = listOf(HeritageDoMock.AACHEN_CATHEDRAL)).blockingAwait()

        val dataSourceFactory = heritageDao.obtainAll()
        // Hack to obtain page items from room
        val heritages = (dataSourceFactory.create() as LimitOffsetDataSource).loadRange(0, 1)

        assertThat(heritages[0]).isEqualTo(HeritageDetailMock.AACHEN_CATHEDRAL)
    }
}