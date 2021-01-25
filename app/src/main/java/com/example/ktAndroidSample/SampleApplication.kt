package com.example.ktAndroidSample

import android.app.Application
import android.content.Context

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object{
        lateinit var appContext: Context
    }
}