package com.example.jetpackdemo.ui.main


import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityMainBinding
import com.example.jetpackdemo.kotlinLearn.show
import com.example.jetpackdemo.ui.main.ui.dashboard.DashboardFragment
import com.example.jetpackdemo.ui.main.ui.home.HomeFragment
import com.example.jetpackdemo.ui.main.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, HomeFragment())
            .addToBackStack(null).commit()

        val fragmentManager = this.supportFragmentManager
        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_container, HomeFragment()).commit()
                }

                R.id.navigation_dashboard -> {
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_container, DashboardFragment()).commit()
                }

                R.id.navigation_notifications -> {
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_container, NotificationsFragment()).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    fun hideBottomNavigation() {
        val param = binding.mainFragmentContainer.layoutParams as ConstraintLayout.LayoutParams
        param.setMargins(0, 0, 0, 0)
        binding.mainFragmentContainer.layoutParams = param
        binding.navView.visibility = GONE
    }

    fun showBottomNavigation(){
        if(binding.navView.visibility != VISIBLE){
            val param = binding.mainFragmentContainer.layoutParams as ConstraintLayout.LayoutParams
            param.setMargins(0,0,0,0)
            binding.mainFragmentContainer.layoutParams = param
            binding.navView.visibility = VISIBLE
        }
    }
}