package com.example.ktAndroidSample.viewPager2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.FragmentSample1Binding


class SampleFragment1 : Fragment() {

    lateinit var binding: FragmentSample1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSample1Binding.inflate(inflater,container,false)
        binding.apply {
            word.setText(R.string.F1)
            content.setBackgroundResource(R.color.CornflowerBlue)
        }

        return binding.root
    }
}