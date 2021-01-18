package com.example.ktAndroidSample.service

import android.app.*
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.ktAndroidSample.R
import java.io.IOException



class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        channelBuilder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notificationOnStart()
        return START_NOT_STICKY
    }

    private fun channelBuilder(){
        val channel = NotificationChannel(
                "myservice_notification_channel",
                getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    private fun notificationOnStart(){

        val intent = Intent(applicationContext,ServiceActivity::class.java)
        intent.putExtra(KEY_NOTIFY,true)

        val stopServiceIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val notification = NotificationCompat.Builder(applicationContext,"myservice_notification_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(getString(R.string.msg_notification_title_start))
                .setContentText(getString(R.string.msg_notification_text_start))
                .setContentIntent(stopServiceIntent)
                .setAutoCancel(true)
                .build()
        startForeground(1,notification)
    }

    private fun notificationOnStop(){
        val builder = NotificationCompat.Builder(applicationContext,"myservice_notification_channel").apply {
            setSmallIcon(android.R.drawable.ic_dialog_info)
            setContentTitle(getString(R.string.msg_notification_title_finish))
            setContentText(getString(R.string.msg_notification_text_finish))
        }
        val notification = builder.build()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0,notification)
    }

    override fun onDestroy() {
        notificationOnStop()
    }

    companion object{
        val KEY_NOTIFY = "key_notify"
    }
}