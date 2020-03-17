package com.delbel.heritage.gateway.source

import android.content.res.AssetManager
import com.delbel.heritage.gateway.model.HeritageDo
import com.delbel.heritage.gateway.source.Heritages.Companion.AACHEN_CATHEDRAL
import com.delbel.heritage.gateway.source.Heritages.Companion.CITY_OF_QUITO
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class AssetsDataSourceTest {

    companion object {
        private const val ASSETS_FILE_NAME = "heritages.json"
        private const val MOCKS_FILE_NAME = "/mock/aachen-quito-heritages.json"
    }

    private lateinit var testObserver: TestObserver<List<HeritageDo>>

    @Before
    fun before() {
        testObserver = TestObserver()
    }

    @After
    fun after() = testObserver.dispose()

    @Test
    fun `obtainAll should emit list`() {
        val assetManager = mock<AssetManager> {
            on { open(ASSETS_FILE_NAME) } doReturn javaClass.getResourceAsStream(MOCKS_FILE_NAME)!!
        }
        val dataSource = AssetsDataSource(assetManager, jsonParser = Gson())

        dataSource.obtainAll().subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValue(listOf(AACHEN_CATHEDRAL, CITY_OF_QUITO))
    }

    @Test
    fun `obtainAll with reading buffer error should emit an error`() {
        val expectedError = mock<IOException>()
        val assetManager = mock<AssetManager> {
            on { open(ASSETS_FILE_NAME) } doThrow expectedError
        }
        val dataSource = AssetsDataSource(assetManager, jsonParser = mock())

        dataSource.obtainAll().subscribe(testObserver)

        testObserver.assertError(expectedError)
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
    }
}