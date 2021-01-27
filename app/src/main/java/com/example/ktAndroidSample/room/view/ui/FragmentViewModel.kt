package com.example.ktAndroidSample.room.view.ui

import androidx.lifecycle.ViewModel
import com.example.ktAndroidSample.SampleApplication
import com.example.ktAndroidSample.room.SampleDB
import com.example.ktAndroidSample.room.SampleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class FragmentViewModel : ViewModel(){

    val db = SampleDB.getInstance(SampleApplication.appContext)

    suspend fun getEntity():SampleEntity {
          return db.sampleDao().getUser(1)
    }
    suspend fun delateData(sampleEntity: SampleEntity){
        db.sampleDao().delete(sampleEntity)
    }

}