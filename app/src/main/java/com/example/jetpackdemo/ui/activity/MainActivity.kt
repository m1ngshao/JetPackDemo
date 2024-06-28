package com.example.jetpackdemo.ui.activity


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony.Sms.Conversations
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityMainBinding
import com.example.jetpackdemo.ui.fragment.DashboardFragment
import com.example.jetpackdemo.ui.fragment.HomeFragment
import com.example.jetpackdemo.ui.fragment.ListItemDetailFragment
import com.example.jetpackdemo.ui.fragment.NotificationsFragment
import com.google.android.material.snackbar.Snackbar


const val REQUEST_CODE_POST_NOTIFICATION = 1000
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val KEY_TEXT_REPLY = "key_text_reply"
    companion object{
        var deepLinkAid: Long? = null
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
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
        showNotification()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach {
            if(it != PackageManager.PERMISSION_GRANTED){
                Snackbar.make(binding.snackarMainActivity,"您拒绝授予权限，无法发送通知",Snackbar.LENGTH_SHORT).show()
                return
            }
            showNotification()
        }
    }

    fun showNotification(){
        val intent = Intent(this,AlertDetailActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)

        val builder = NotificationCompat.Builder(this,"notification1")
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line so I chose to fold it up"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_RECOMMENDATION)
            .setFullScreenIntent(pendingIntent,true)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setOnlyAlertOnce(true)

        with(NotificationManagerCompat.from(this)){
            if(ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                )  != PackageManager.PERMISSION_GRANTED
            ){
                ActivityCompat.requestPermissions(this@MainActivity,arrayOf(Manifest.permission.POST_NOTIFICATIONS),REQUEST_CODE_POST_NOTIFICATION)
                return@with
            }
            notify(REQUEST_CODE_POST_NOTIFICATION,builder.build())
        }
    }

    private fun showProgressNotification(){
//        startService(Intent(this,ProgressService(this)::class.java))
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

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name  = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("notification1",name, importance).apply {
                description = descriptionText
            }

            val progressChannel = NotificationChannel("progressNotification",name, importance).apply {
                description = descriptionText
            }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}