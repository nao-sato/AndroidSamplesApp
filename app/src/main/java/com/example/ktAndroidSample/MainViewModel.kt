package com.example.ktAndroidSample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ktAndroidSample.picker.PickerActivity
import com.example.ktAndroidSample.mediaPlayer.MediaPlayerActivity
import com.example.ktAndroidSample.recyclerView.RecyclerViewActivity
import com.example.ktAndroidSample.service.ServiceActivity

class MainViewModel: ViewModel() {
    val activityPairs = MutableLiveData<List<Pair<String, String>>>()

    fun initData() {
        activityPairs.postValue(
            listOf(
                    Pair("MediaPlayer", MediaPlayerActivity::class.java.simpleName),
                    Pair("Service", ServiceActivity::class.java.simpleName),
                    Pair("Picker", PickerActivity::class.java.simpleName),
                    Pair("RecyclerView", RecyclerViewActivity::class.java.simpleName)
            )
        )
    }
}