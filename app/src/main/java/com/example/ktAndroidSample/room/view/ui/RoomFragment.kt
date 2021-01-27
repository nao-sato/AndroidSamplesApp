package com.example.ktAndroidSample.room.view.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ktAndroidSample.databinding.FragmentRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        CoroutineScope(Dispatchers.IO).launch {
            val entity = viewModel.getHonorific()
            withContext(Dispatchers.Main){
                binding.txtHonorific.text = entity.honorific
                binding.txtName.text = entity.name
            }
        }

    }


}