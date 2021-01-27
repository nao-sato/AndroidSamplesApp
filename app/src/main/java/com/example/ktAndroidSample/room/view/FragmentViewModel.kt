package com.example.ktAndroidSample.room.view

import androidx.lifecycle.ViewModel
import com.example.ktAndroidSample.SampleApplication
import com.example.ktAndroidSample.room.SampleDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class FragmentViewModel : ViewModel(){

    val db = SampleDB.getInstance(SampleApplication.appContext)
    var fHonorific: String = ""
    var fName: String = ""

    fun getHonorific(){
        CoroutineScope(Dispatchers.IO).launch {
            fHonorific = db.sampleDao().getUser(1).honorific
        }
    }
    fun getName(){
        CoroutineScope(Dispatchers.IO).launch {
            fName = db.sampleDao().getUser(1).name
        }
    }

}