package com.resurrection.base.core.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseHolder<T>(
    val binding: ViewDataBinding,
    val itemId: Int?,
    val onItemClick: ((T) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        itemId?.let { binding.setVariable(it, item) }
        itemView.setOnClickListener { onItemClick?.invoke((item)) }
    }
}