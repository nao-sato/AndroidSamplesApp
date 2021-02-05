package com.example.ktAndroidSample

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ktAndroidSample.picker.PickerActivity
import com.example.ktAndroidSample.mediaPlayer.MediaPlayerActivity
import com.example.ktAndroidSample.recyclerView.RecyclerViewActivity
import com.example.ktAndroidSample.room.view.RoomActivity
import com.example.ktAndroidSample.service.ServiceActivity
import com.example.ktAndroidSample.viewPager2.ViewPager2Activity

class MainViewModel: ViewModel() {
    val activityPairs = MutableLiveData<List<Triple<String, String,Int>>>()
    val clickGuid = MutableLiveData<Int>()

    fun initData() {
        activityPairs.postValue(
                listOf(
                        Triple("MediaPlayer", MediaPlayerActivity::class.java.simpleName,R.string.media_desc),
                        Triple("Service", ServiceActivity::class.java.simpleName,R.string.service_desc),
                        Triple("Picker", PickerActivity::class.java.simpleName,R.string.picker_desc),
                        Triple("RecyclerView", RecyclerViewActivity::class.java.simpleName,R.string.recycle_desc),
                        Triple("ViewPager2", ViewPager2Activity::class.java.simpleName,R.string.viewpager_desc),
                        Triple("Room",RoomActivity::class.java.simpleName,R.string.room_desc)
                )
        )
    }
}