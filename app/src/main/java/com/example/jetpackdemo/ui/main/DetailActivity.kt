package com.example.jetpackdemo.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.kotlinLearn.main

class DetailActivity : AppCompatActivity() {
    companion object{
        var deepLinkAid: Long? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        supportFragmentManager.beginTransaction().replace(R.id.detail_container,ListItemDetailFragment())
            .addToBackStack(null).commit()
        val uri = intent.data
        deepLinkAid = uri?.getQueryParameter("aid")?.toLong()

        reStartActivity(intent,this)
    }

    fun reStartActivity(intent : Intent , context: Context){
        val  intentList = mutableListOf<Intent>()
        val mainIntent = Intent(context,MainActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intentList.add(mainIntent)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intentList.add(intent)
        context.startActivities(intentList.toTypedArray())
    }
}