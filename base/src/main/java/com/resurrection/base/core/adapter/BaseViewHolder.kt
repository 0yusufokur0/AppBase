package com.resurrection.base.core.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<Model, VDB : ViewDataBinding>(
    private val binding: VDB,
    private val itemId: Int?,
    private val setBind: ((VDB, Model) -> Unit)? = null,
    private val itemClick: ((Model) -> Unit)? = null,
    private val itemLongClick: ((Model) -> Boolean)? = null,
) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(item: Model) {
        itemId?.let { binding.setVariable(it, item) }
        setBind?.invoke(binding, item)

        itemView.setOnClickListener {
            itemClick?.invoke((item))
        }

        itemLongClick?.let { longClick ->
            itemView.setOnLongClickListener {
                longClick(item)
            }
        }
    }
}