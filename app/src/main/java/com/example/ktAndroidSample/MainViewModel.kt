package com.example.ktAndroidSample

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.core.view.KeyEventDispatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afollestad.materialdialogs.MaterialDialog
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

    fun initDialog(data:Int,context: Context){
        MaterialDialog(context).show {
            when(data){
                R.string.media_desc ->  title(R.string.media_name)
                R.string.service_desc -> title(R.string.service_name)
                R.string.picker_desc -> title(R.string.picker_name)
                R.string.recycle_desc -> title(R.string.recycler_name)
                R.string.viewpager_desc -> title(R.string.pager2_name)
                R.string.room_desc -> title(R.string.room_name)
                else -> title(null)
            }
            message(data)
            negativeButton(R.string.warn_back)
        }
    }
}