package com.example.ktAndroidSample.recyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.ActivityRecyclerViewBinding
import com.example.ktAndroidSample.databinding.RowRecyclerViewBinding

class ArticleView: RecyclerView {

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet?) : super(ctx, attrs)
    constructor(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr)

    val customAdapter by lazy { Adapter(context) }

    init {
        adapter = customAdapter
        layoutManager = LinearLayoutManager(context)
    }

    class Adapter(val context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val items = mutableListOf<String>()

        fun refresh(list: List<String>) {
            items.apply {
                clear()
                addAll(list)
            }
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
//            ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.row_recycler_view, null, false))
            ItemViewHolder(RowRecyclerViewBinding.inflate(LayoutInflater.from(context), parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (holder is ItemViewHolder)
                onBindViewHolder(holder, position)
        }

        private fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val data = items[position]
            holder.binding.view.text = data
            holder.binding.view.setBackgroundColor(ContextCompat.getColor(context, if (position % 2 == 0) R.color.CornflowerBlue else R.color.LightSteelBlue))
        }

        class ItemViewHolder(val binding: RowRecyclerViewBinding): ViewHolder(binding.root)
    }
}