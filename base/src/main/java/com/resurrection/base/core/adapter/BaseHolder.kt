package com.resurrection.base.core.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseHolder<T>(
    private var binding: ViewDataBinding,
    private var itemId: Int,
    var onItemClick: (T) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.setVariable(itemId, item)
        itemView.setOnClickListener { onItemClick((item)) }
    }
}