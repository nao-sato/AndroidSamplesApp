package com.example.ktAndroidSample.room.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.room.view.ui.RequireFragment

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, RequireFragment())
                .commit()
        }
    }

    companion object{
        fun start(activity: Activity) = activity.startActivity(Intent(activity, RoomActivity::class.java))
    }
}