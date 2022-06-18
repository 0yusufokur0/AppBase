package com.resurrection.base.core.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseHolder<Model,VDB:ViewDataBinding>(
    open val binding: VDB,
    open val itemId: Int?,
    open val onItemClick: ((Model) -> Unit)? = null,
    open val setBind :((VDB,Model) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(item: Model) {
        itemId?.let { binding.setVariable(it, item) }
        setBind?.invoke(binding,item)
        itemView.setOnClickListener { onItemClick?.invoke((item)) }
    }
}