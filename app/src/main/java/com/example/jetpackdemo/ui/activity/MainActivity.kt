package com.example.jetpackdemo.ui.activity


import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityMainBinding
import com.example.jetpackdemo.ui.fragment.DashboardFragment
import com.example.jetpackdemo.ui.fragment.HomeFragment
import com.example.jetpackdemo.ui.fragment.ListItemDetailFragment
import com.example.jetpackdemo.ui.fragment.NotificationsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object{
        var deepLinkAid: Long? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val uri = intent.data
        deepLinkAid = uri?.getQueryParameter("aid")?.toLong()
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
        if(deepLinkAid != null) {
            supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, ListItemDetailFragment())
                .addToBackStack(null).commit()
            hideBottomNavigation()
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