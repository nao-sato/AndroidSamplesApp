package com.example.ktAndroidSample.ImplicitIntent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.ActivityMapBinding
import com.example.ktAndroidSample.mediaPlayer.MediaPlayerActivity

class MapActivity : AppCompatActivity() {

    lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initBinding()
    }

    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map)
    }

    companion object {
        fun start(activity: Activity) = activity.startActivity(Intent(activity, MapActivity::class.java))
    }
}