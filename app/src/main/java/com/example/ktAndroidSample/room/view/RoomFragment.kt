package com.example.ktAndroidSample.room.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ktAndroidSample.databinding.FragmentRoomBinding

class RoomFragment : Fragment() {

    lateinit var binding: FragmentRoomBinding
    private val viewModel: FragmentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(layoutInflater,container,false)
        initialize()
        return binding.root
    }

    private fun initialize(){
        binding.apply {
            honorific = viewModel.fHonorific
            name = viewModel.fName
        }
    }


}