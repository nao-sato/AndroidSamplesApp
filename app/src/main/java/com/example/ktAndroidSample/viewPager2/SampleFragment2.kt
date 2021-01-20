package com.example.ktAndroidSample.viewPager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.FragmentSample1Binding

class SampleFragment2 : Fragment() {

    lateinit var binding: FragmentSample1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSample1Binding.inflate(inflater,container,false)
        binding.apply {
            word.setText(R.string.F2)
            content.setBackgroundResource(R.color.LightSteelBlue)
        }
        return binding.root

    }
}