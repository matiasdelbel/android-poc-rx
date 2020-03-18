package com.delbel.heritage.gateway.source

import android.content.res.AssetManager
import com.delbel.heritage.gateway.mock.gateway.HeritageDoMock.Companion.AACHEN_CATHEDRAL
import com.delbel.heritage.gateway.mock.gateway.HeritageDoMock.Companion.CITY_OF_QUITO
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.io.IOException

class AssetsDataSourceTest {

    companion object {
        private const val ASSETS_FILE_NAME = "heritages.json"
        private const val MOCKS_FILE_NAME = "/mock/aachen-quito-heritages.json"
    }

    @Test
    fun `obtainAll should emit list`() {
        val assetManager = mock<AssetManager> {
            on { open(ASSETS_FILE_NAME) } doReturn javaClass.getResourceAsStream(MOCKS_FILE_NAME)!!
        }
        val dataSource = AssetsDataSource(
            assetsManager = assetManager,
            jsonParser = Gson(),
            computationScheduler = Schedulers.trampoline()
        )

        val testObserver = dataSource.obtainAll().test()

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
        val dataSource = AssetsDataSource(
            assetsManager = assetManager,
            jsonParser = mock(),
            computationScheduler = Schedulers.trampoline()
        )

        val testObserver = dataSource.obtainAll().test()

        testObserver.assertError(expectedError)
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
    }
}