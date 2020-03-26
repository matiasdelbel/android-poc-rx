package com.delbel.heritage.presentation.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.delbel.heritage.domain.repository.HeritageRepository
import javax.inject.Inject

internal class ListingViewModel @Inject constructor(repository: HeritageRepository) : ViewModel() {

    val listing = repository.obtainAll().toLiveData()
}