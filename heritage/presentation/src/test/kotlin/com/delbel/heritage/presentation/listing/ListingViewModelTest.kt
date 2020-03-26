package com.delbel.heritage.presentation.listing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.delbel.heritage.domain.HeritageDetail
import com.delbel.heritage.domain.repository.HeritageRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test

class ListingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `listing should delegate task to the repository`() {
        val repository = mock<HeritageRepository> { on { obtainAll() } doReturn mock() }
        val viewModel = ListingViewModel(repository)

        val observer = mock<Observer<PagedList<HeritageDetail>>>()
        viewModel.listing.observeForever(observer)

        verify(repository).obtainAll()
    }
}