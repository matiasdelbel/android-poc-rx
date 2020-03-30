package com.delbel.heritage.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.screen_main.*

class MainScreen : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_main)
        navController = findNavController(R.id.navigationHost)

        setUpToolbar()
    }

    private fun setUpToolbar() {
        val toolbarConfig = AppBarConfiguration(topLevelDestinationIds = setOf(R.id.htg_listing))

        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, toolbarConfig)
    }
}