package com.avenger.timesaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.avenger.timesaver.databinding.ActivityMainBinding
import com.avenger.timesaver.interfaces.NavigationToggleComponent
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationToggleComponent {
    private lateinit var binding: ActivityMainBinding

    companion object {
        lateinit var navToggleInterface: NavigationToggleComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeNavigationSetup()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun homeNavigationSetup() {
        navToggleInterface = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_mainscreen)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.tipsFragment,
                R.id.nearByFragment,
                R.id.shopFragment,
                R.id.profileFragment
            )
        )
        navView.setupWithNavController(navController)
    }

    override fun setNavigation(flag: Boolean) {
        if (flag) {
            nav_view.visibility = View.VISIBLE
        } else
            nav_view.visibility = View.GONE
    }

    override fun navigateToProfile() {

    }
}