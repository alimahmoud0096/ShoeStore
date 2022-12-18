package com.udacity.shoestore

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        setSupportActionBar(binding.toolbar)
        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            binding.myNavHostFragment.id
        ) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.shoeDetailFragment,
                R.id.shoeListingFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()||super.onSupportNavigateUp()
    }
}
