package com.delbel.heritage.presentation.map

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.delbel.dagger.viewmodel.savedstate.ext.viewModels
import com.delbel.heritage.domain.HeritageCoordinate
import com.delbel.heritage.presentation.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MapScreen : Fragment(R.layout.htg_screen_map) {

    companion object {
        private const val MAP_ZOOM = 15f
    }

    @Inject
    internal lateinit var factory: MapViewModel.Factory
    private val viewModel: MapViewModel by viewModels { factory }

    private val mapView by lazy { childFragmentManager.findFragmentById(R.id.htg_screen_map_view) as SupportMapFragment }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.getMapAsync(::observeHeritageCoordinates)
    }

    private fun observeHeritageCoordinates(map: GoogleMap) {
        viewModel.coordinates.observe(viewLifecycleOwner, Observer { point ->
            showPointOnMap(point, map)
        })
    }

    private fun showPointOnMap(point: HeritageCoordinate, map: GoogleMap) {
        val markerOption = MarkerOptions()
            .position(LatLng(point.lat, point.lng))
            .title(point.name)

        with(map.addMarker(markerOption)) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, MAP_ZOOM))
        }
    }
}