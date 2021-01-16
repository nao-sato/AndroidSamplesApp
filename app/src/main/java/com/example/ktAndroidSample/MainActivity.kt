package com.example.ktAndroidSample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.ktAndroidSample.Picker.PickerActivity
import com.example.ktAndroidSample.databinding.ActivityMainBinding
import com.example.ktAndroidSample.mediaPlayer.MediaPlayerActivity
import com.example.ktAndroidSample.service.ServiceActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        initBinding()
        initViewModel()
        initLayout()
        initData()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initViewModel() {
        viewModel.activityPairs.observe(this@MainActivity, Observer {
            binding.samplesView.customAdapter.refresh(it)
        })
    }


    private fun initLayout() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.samplesView.customAdapter.callback = object : SamplesView.Callback {
            override fun onClick(ActivitySimpleName: String) {
                startActivity(ActivitySimpleName)
            }
        }
    }

    private fun startActivity(activitySimpleName: String) {
        when (activitySimpleName) {
            MediaPlayerActivity::class.java.simpleName -> MediaPlayerActivity.start(this)
            ServiceActivity::class.java.simpleName -> ServiceActivity.start(this)
            PickerActivity::class.java.simpleName -> PickerActivity.start(this)
        }
    }

    private fun initData(){
        viewModel.initData()
    }
}