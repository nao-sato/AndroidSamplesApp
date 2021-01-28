package com.example.ktAndroidSample.room.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.SampleApplication
import com.example.ktAndroidSample.room.SampleDB
import com.example.ktAndroidSample.room.view.ui.RequireFragment
import com.example.ktAndroidSample.room.view.ui.GreetFragment
import kotlinx.coroutines.*

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val db = SampleDB.getInstance(SampleApplication.appContext)

        CoroutineScope(Dispatchers.IO).launch {
            val data = db.sampleDao().loadAllTodo()
            if (data == null) {
                supportFragmentManager.beginTransaction().replace(R.id.container,RequireFragment()).commit()
            }else {
                supportFragmentManager.beginTransaction().replace(R.id.container,GreetFragment()).commit()
            }
        }
    }

    companion object{
        fun start(activity: Activity) = activity.startActivity(Intent(activity, RoomActivity::class.java))
    }
}