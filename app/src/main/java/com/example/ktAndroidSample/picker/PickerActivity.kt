package com.example.ktAndroidSample.picker

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil.setContentView
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.ActivityPickerBinding
import java.util.*

class PickerActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    lateinit var binding: ActivityPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContentView(this,R.layout.activity_picker)
        binding.apply {
            btDate.setOnClickListener{ showDatePickerDialog() }
            btTime.setOnClickListener{ showTimePickerDialog() }
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val dateStr = String.format(Locale.US, "%d/%d/%d", year, monthOfYear+1, dayOfMonth)
        binding.txtDate.text = dateStr
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val timeStr = String.format(Locale.US, "%d:%d", hourOfDay, minute)
        binding.txtTime.text = timeStr
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun showTimePickerDialog() {
        val newFragment = TimePickerFragment()
        newFragment.show(supportFragmentManager, "timePicker")
    }


    companion object{
        fun start(activity: Activity) = activity.startActivity(Intent(activity, PickerActivity::class.java))
    }


}