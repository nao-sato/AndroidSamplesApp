package com.example.ktAndroidSample.viewPager2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.ktAndroidSample.R

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.example.ktAndroidSample.databinding.ActivityViewPager2Binding
import com.example.ktAndroidSample.recyclerView.RecyclerViewActivity


private const val NUM_PAGES = 3

class ViewPager2Activity : FragmentActivity() {

    private lateinit var binding: ActivityViewPager2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onBackPressed() {
        if (binding.pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.pager.currentItem = binding.pager.currentItem - 1
        }
    }

    private fun initialize(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_pager2)
        binding.pager.adapter = ScreenSlidePagerAdapter(this)
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES
        override fun createFragment(position: Int) = when(position) {
            0 -> SampleFragment1()
            1 -> SampleFragment2()
            else -> SampleFragment3()
        }
    }

    companion object{
        fun start(activity: Activity){ activity.startActivity(Intent(activity, ViewPager2Activity::class.java)) }
    }
}
