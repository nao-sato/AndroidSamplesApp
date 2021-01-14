package com.example.ktAndroidSample.service

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.ActivityServiceBinding

lateinit var  binding: ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service)
        initialize()
    }

    private fun initialize(){
        onPlayButton()
        onStopButton()
        onStartFromNotification()
    }

    private fun onPlayButton(){
        val intent = Intent(applicationContext, SoundManageService::class.java)
        binding.btPlay.setOnClickListener {
            startService(intent)
            binding.apply {
                btPlay.isEnabled = false
                btStop.isEnabled = true
            }
        }
    }
    private fun onStopButton(){
        val intent = Intent(applicationContext, SoundManageService::class.java)
        binding.btStop.setOnClickListener {
            stopService(intent)
            binding.apply {
                btPlay.isEnabled = true
                btStop.isEnabled = false
            }
        }
    }
    private fun onStartFromNotification(){
        val fromNotification = intent.getBooleanExtra(SoundManageService.KEY_BOOL, false)
        if (fromNotification)
            binding.apply {
                btPlay.isEnabled = false
                btStop.isEnabled = true
            }
    }
    companion object{
        fun start(activity: Activity) = activity.startActivity(Intent(activity,ServiceActivity::class.java))
    }

}