package com.example.ktAndroidSample.mediaPlayer

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.ActivityMediaBinding
import java.io.IOException

class MediaPlayerActivity : AppCompatActivity() {

    lateinit var binding: ActivityMediaBinding
    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_media)
        player = MediaPlayer()
        initialize()
    }

    private fun initialize(){
        onPrepare()
        onPlayButtonClick()
        onBackButtonClick()
        onForwardButtonClick()
        onLoopSwitch()
    }


    private fun onPrepare(){
        val mediaFileUri: Uri = Uri.parse("android.resource://${packageName}/${R.raw.sample1_music}")
        try {
            player?.apply{
                setDataSource(applicationContext,mediaFileUri)
                setOnPreparedListener{
                    binding.apply {
                        btPlay.isEnabled = true
                        btBack.isEnabled = true
                        btForward.isEnabled = true
                    }
                }
                setOnCompletionListener{
                    player?.let {
                        if (!it.isLooping){
                            binding.btPlay.setText(R.string.bt_play_play)
                        }
                    }
                }
                prepareAsync()
            }
        }catch (ex: IllegalArgumentException){
            Log.d("メディアプレーヤー準備時の例外発生", ex.toString())
        }
        catch (ex: IOException){
            Log.d("メディアプレーヤー準備時の例外発生",ex.toString())
        }
    }
    private fun onPlayButtonClick(){
        binding.btPlay.setOnClickListener{
            player?.let {
                if (it.isPlaying) {
                    it.pause()
                    binding.btPlay.setText(R.string.bt_play_play)
                } else {
                    it.start()
                    binding.btPlay.setText(R.string.bt_play_pause)
                }
            }
        }
    }
    private fun onBackButtonClick(){
        binding.btBack.setOnClickListener{
            player?.seekTo(0)
        }
    }
    private fun onForwardButtonClick(){
        binding.btForward.setOnClickListener{
            player?.let {
                val duration = it.duration
                it.seekTo(duration)
                if (!it.isPlaying)
                    it.start()
            }
        }
    }
    private fun onLoopSwitch(){
        binding.swLoop.setOnCheckedChangeListener{ _: CompoundButton, isChecked: Boolean ->
            player?.isLooping = isChecked
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        player?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
            player = null
        }
    }

    companion object {
        fun start(activity: Activity) = activity.startActivity(Intent(activity, MediaPlayerActivity::class.java))
    }
}
