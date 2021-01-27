package com.example.ktAndroidSample.room.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.ActivityRoomBinding
import com.example.ktAndroidSample.picker.PickerActivity

class RoomActivity : AppCompatActivity() {

    lateinit var binding: ActivityRoomBinding
    private val activityViewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initView()
        initListener()
    }

    private fun initView(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_room)
        binding.viewModel = activityViewModel
    }

    private fun initListener(){
        initRadio()
        initClick()
    }

    private fun initRadio(){
        binding.gender.setOnClickListener {
            when(it) {
                binding.radioMale -> binding.radioFemale.isChecked = false
                binding.radioFemale -> binding.radioMale.isChecked = false
            }
        }
    }

    private fun initClick(){

        binding.btConfirm.setOnClickListener{
            if (activityViewModel.name.isEmpty()){
                MaterialDialog(this).show {
                    title(R.string.warn_empty_name)
                    negativeButton(R.string.warn_back)
                }
            }else if (!binding.radioMale.isChecked && !binding.radioFemale.isChecked){
                MaterialDialog(this).show {
                    title(R.string.warn_empty_gender)
                    negativeButton(R.string.warn_back)
                }
            }else{
                when(binding.radioMale.isChecked){
                    true -> activityViewModel.honorific = "Mr."
                    false -> activityViewModel.honorific = "Ms."
                }
                activityViewModel.setData()
                //startFragment
            }

        }
    }
    companion object{
        fun start(activity: Activity) = activity.startActivity(Intent(activity, RoomActivity::class.java))
    }
}