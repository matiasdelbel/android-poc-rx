package com.delbel.heritage.presentation.listing

import android.content.Context
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.delbel.heritage.domain.HeritageDetail
import com.delbel.heritage.presentation.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlinx.android.synthetic.main.htg_screen_listing.htg_screen_listing_list as listing
import kotlinx.android.synthetic.main.htg_screen_listing.htg_screen_listing_loading as loading

class ListingScreen : Fragment(R.layout.htg_screen_listing) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ListingViewModel> { viewModelFactory }

    private val adapter = HeritageAdapter(onTouch = {
        findNavController().navigate(ListingScreenDirections.listingToMap(heritageId = it.id))
    })

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewInitialState()

        viewModel.listing.observe(this, Observer(::handleHeritagePages))
    }

    private fun setUpViewInitialState() {
        listing.adapter = adapter
        listing.addItemDecoration(MarginItemDecoration())
    }

    private fun handleHeritagePages(page: PagedList<HeritageDetail>) {
        loading.isVisible = false
        adapter.submitList(page)
    }
}