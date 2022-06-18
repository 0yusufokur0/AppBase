package com.resurrection.base.core.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseHolder<Model,VDB:ViewDataBinding>(
    open val binding: VDB,
    open val itemId: Int?,
    private val itemClick: ((Model) -> Unit)? = null,
    private val itemLongClick: ((Model) -> Unit)? = null,
    open val setBind :((VDB,Model) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(item: Model) {
        itemId?.let { binding.setVariable(it, item) }
        setBind?.invoke(binding,item)
        itemView.setOnClickListener { itemClick?.invoke((item)) }
        itemView.setOnLongClickListener {
            itemLongClick?.invoke((item))
            true
        }
    }
}