package com.example.ktAndroidSample.recyclerView

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var binding:ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initBinding()
        initView()
        initSwipeRefreshLayout()
    }

    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recycler_view)
    }

    private fun initView(){
        binding.view.customAdapter.refresh(makeIndex())

    }
    private fun initSwipeRefreshLayout() {
        binding.swipe.setOnRefreshListener {
            initData()
        }
    }

    private fun initData() {
        binding.view.customAdapter.refresh(makeIndex())
        binding.swipe.isRefreshing = false
    }

    private fun makeIndex(): List<String>{
        val list = mutableListOf<String>()
        for (number in 0 .. 200) {
            list.add("$number")
        }
        return list
    }

    companion object{
        fun start(activity: Activity){ activity.startActivity(Intent(activity,RecyclerViewActivity::class.java))}
    }
}