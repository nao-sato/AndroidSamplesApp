package com.example.ktAndroidSample

import android.os.Bundle
import android.os.Message
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.example.ktAndroidSample.ImplicitIntent.MapActivity
import com.example.ktAndroidSample.picker.PickerActivity
import com.example.ktAndroidSample.databinding.ActivityMainBinding
import com.example.ktAndroidSample.mediaPlayer.MediaPlayerActivity
import com.example.ktAndroidSample.recyclerView.RecyclerViewActivity
import com.example.ktAndroidSample.room.view.RoomActivity
import com.example.ktAndroidSample.service.ServiceActivity
import com.example.ktAndroidSample.viewPager2.ViewPager2Activity

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
        viewModel.apply {
            activityTriple.observe(this@MainActivity, Observer { binding.samplesView.customAdapter.refresh(it) })
            clickGuid.observe(this@MainActivity, Observer { viewModel.initDialog(it,this@MainActivity) })
        }
    }

    private fun initData(){
        viewModel.initData()
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
            RecyclerViewActivity::class.java.simpleName -> RecyclerViewActivity.start(this)
            ViewPager2Activity::class.java.simpleName -> ViewPager2Activity.start(this)
            RoomActivity::class.java.simpleName -> RoomActivity.start(this)
            MapActivity::class.java.simpleName -> MapActivity.start(this)
        }
    }
}