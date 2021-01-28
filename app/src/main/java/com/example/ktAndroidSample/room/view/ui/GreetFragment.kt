package com.example.ktAndroidSample.room.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.FragmentRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GreetFragment : Fragment() {

    lateinit var binding: FragmentRoomBinding
    private val viewModel: FragmentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(layoutInflater,container,false)
        initialize()
        return binding.root
    }

    private fun initialize(){
        initView()
        initListener()
    }

    private fun initView(){
        CoroutineScope(Dispatchers.IO).launch {
            val entity = viewModel.getEntity()
            withContext(Dispatchers.Main){
                binding.txtHonorific.text = entity.honorific
                binding.txtName.text = entity.name
            }
        }
    }

    private fun initListener(){
        binding.btChangeName.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val entity = viewModel.getEntity()
                viewModel.delateData(entity)
            }
            launcherRequireFragment()
        }
    }

    private fun launcherRequireFragment(){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, RequireFragment())?.commit()
    }


}