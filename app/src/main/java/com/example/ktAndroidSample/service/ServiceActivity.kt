package com.example.ktAndroidSample.service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

    override fun onDestroy() {
        super.onDestroy()
        stopService(intent)
    }

    private fun initialize(){
        onPlayButton()
        onStopButton()
        startFromNotification()
    }

    private fun onPlayButton(){
        val intent = Intent(applicationContext, MyService::class.java)
        binding.btPlay.setOnClickListener {
            startForegroundService(intent)
            binding.apply {
                btPlay.isEnabled = false
                btStop.isEnabled = true
            }
            Toast.makeText(applicationContext,"サービスが開始されました。", Toast.LENGTH_LONG).show()
        }
    }

    private fun onStopButton(){
        val intent = Intent(applicationContext, MyService::class.java)
        binding.btStop.setOnClickListener {
            stopService(intent)
            binding.apply {
                btPlay.isEnabled = true
                btStop.isEnabled = false
            }
            Toast.makeText(applicationContext,"サービスを停止しました。", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startFromNotification(){
        val fromNotify = intent.getBooleanExtra(MyService.KEY_NOTIFY,false)
        if (fromNotify)
            binding.apply {
                btPlay.isEnabled = false
                btStop.isEnabled = true
            }

    }

    companion object{
        fun start(activity: Activity) = activity.startActivity(Intent(activity,ServiceActivity::class.java))
    }

}