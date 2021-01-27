package com.example.ktAndroidSample.room.view.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.FragmentRequireBinding
import com.example.ktAndroidSample.room.SampleDB

class RequireFragment(private val con: Context) : Fragment() {
    lateinit var binding: FragmentRequireBinding
    private val requireViewModel: RequireViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRequireBinding.inflate(layoutInflater,container,false)
        initialize()
        return binding.root
    }

    private fun initialize(){
        initView()
        initListener()
    }

    private fun initView(){
        binding.viewModel = requireViewModel
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
            if (binding.editTextName.text.isEmpty()){

                    MaterialDialog(con).show {
                        title(R.string.warn_empty_name)
                        negativeButton(R.string.warn_back)
                    }
            }else if (!binding.radioMale.isChecked && !binding.radioFemale.isChecked){
                    MaterialDialog(con).show {
                        title(R.string.warn_empty_gender)
                        negativeButton(R.string.warn_back)
                    }
            }else{
                when(binding.radioMale.isChecked){
                    true -> requireViewModel.honorific = "Mr."
                    false -> requireViewModel.honorific = "Ms."
                }
                requireViewModel.setData(SampleDB.getInstance(con))
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, RoomFragment())?.commit()
            }

        }
    }
}