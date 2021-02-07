package com.example.ktAndroidSample

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktAndroidSample.databinding.ItemBinding

class SamplesView: RecyclerView {

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet?) : super(ctx, attrs)
    constructor(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr)

    val customAdapter by lazy { Adapter(context) }

    init {
        adapter = customAdapter
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(context,LinearLayoutManager(context).orientation))

    }

    class Adapter(val context: Context): RecyclerView.Adapter<ViewHolder>() {

        var callback: Callback? = null
        private val viewModel: MainViewModel by (context as ComponentActivity).viewModels ()

        private val items = mutableListOf<Triple<String, String, Int>>()

        fun refresh(list: List<Triple<String, String, Int>>){
            items.apply {
                clear()
                addAll(list)
            }
            notifyDataSetChanged()
        }
        override fun getItemCount() = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Samples
        = Samples(ItemBinding.inflate(LayoutInflater.from(context),parent,false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (holder is Samples)
                onBindViewHolder(holder, position)
        }

        private fun onBindViewHolder(holder: Samples, position: Int) {
            val data = items[position]
            holder.binding.apply {
                title = data.first
                eachItem.setOnClickListener{ callback?.onClick(data.second)}
                guid.setOnClickListener{viewModel.clickGuid.postValue(data.third)}
            }
        }

        class Samples(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)
    }

    interface Callback {
        fun onClick(ActivitySimpleName: String)
    }

}