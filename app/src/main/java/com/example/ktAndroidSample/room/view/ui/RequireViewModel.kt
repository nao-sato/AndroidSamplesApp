package com.example.ktAndroidSample.room.view.ui

import androidx.lifecycle.ViewModel
import com.example.ktAndroidSample.room.SampleDB
import com.example.ktAndroidSample.room.SampleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequireViewModel : ViewModel() {

    var honorific:String = ""
    var name: String = ""

    fun setData(db: SampleDB){
        CoroutineScope(Dispatchers.IO).launch {
            db.sampleDao().insert(SampleEntity(1,honorific, name))
        }
    }
}