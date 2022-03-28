package com.resurrection.base.core.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseHolder<T>(
    open val binding: ViewDataBinding,
    open val itemId: Int?,
    open val onItemClick: ((T) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(item: T) {
        itemId?.let { binding.setVariable(it, item) }
        itemView.setOnClickListener { onItemClick?.invoke((item)) }
    }
}