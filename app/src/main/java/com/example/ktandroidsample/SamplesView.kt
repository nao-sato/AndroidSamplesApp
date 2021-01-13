package com.example.ktandroidsample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktandroidsample.databinding.ItemBinding

class SamplesView: RecyclerView {

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet?) : super(ctx, attrs)
    constructor(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        ctx,
        attrs,
        defStyleAttr
    )

    val customAdapter by lazy { Adapter(context) }

    init {
        adapter = customAdapter
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
    }

    class Adapter(val context: Context): RecyclerView.Adapter<ViewHolder>() {

        var items = mutableListOf<Pair<String,String>>()

        fun refresh(list: List<Pair<String,String>>){
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

        }

        class Samples(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)
    }

    interface Callback {
        fun onClick(ActivitySimpleName: String)
    }

}