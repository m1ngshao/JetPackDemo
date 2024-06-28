package com.example.jetpackdemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityAlertDetailBinding
import com.google.android.material.snackbar.Snackbar

class AlertDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlertDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlertDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.alertBtn.setOnClickListener {
            Snackbar.make(binding.alertCoordinatorLayout,R.string.show_message,Snackbar.LENGTH_SHORT).show()
        }
    }
}