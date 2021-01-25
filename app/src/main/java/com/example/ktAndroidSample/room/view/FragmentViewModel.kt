package com.example.ktAndroidSample.room.view

import androidx.lifecycle.ViewModel
import com.example.ktAndroidSample.SampleApplication
import com.example.ktAndroidSample.room.SampleDB

class FragmentViewModel : ViewModel(){

    val db = SampleDB.getInstance(SampleApplication.appContext)
    var fHonorific: String = ""
    var fName: String = ""

    fun getHonorific(): String{
        return db.sampleDao().loadAllTodo().honorific
    }
    fun getName():String{
        return db.sampleDao().loadAllTodo().name
    }

}