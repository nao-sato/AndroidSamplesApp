package com.example.ktAndroidSample.Picker

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.FragmentActivity
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.ActivityPickerBinding
import com.example.ktAndroidSample.service.ServiceActivity
import java.util.*

class PickerActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var binding: ActivityPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this,R.layout.activity_picker)
        binding.btDate.setOnClickListener{ showDatePickerDialog() }
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val str = String.format(Locale.US, "%d/%d/%d", year, monthOfYear+1, dayOfMonth)
        binding.txtDate.text = str
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePick()
        newFragment.show(supportFragmentManager, "datePicker")

    }

    companion object{
        fun start(activity: Activity) = activity.startActivity(Intent(activity, PickerActivity::class.java))
    }
}