package com.example.ktAndroidSample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ktAndroidSample.mediaPlayer.MediaPlayerActivity

class MainViewModel: ViewModel() {
    val activityPairs = MutableLiveData<List<Pair<String, String>>>()

    fun initData() {
        activityPairs.postValue(
            listOf(
                Pair("MediaPlayer", MediaPlayerActivity::class.java.simpleName)
            )
        )
    }
}