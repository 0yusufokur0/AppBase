package com.resurrection.base.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class CoreAdapter<Model : Any, VDB : ViewDataBinding>(
    private val layoutResource: Int,
    private val itemId: Int?,
    private var currentList: ArrayList<Model>? = null,
) : RecyclerView.Adapter<BaseHolder<Model, VDB>>() {

    private var itemClick: ((Model) -> Unit)? = null
    private var itemLongClick: ((Model) -> Boolean)? = null
    private lateinit var binding: VDB

    fun setOnItemClickListener(itemClick: (Model) -> Unit) {
        this.itemClick = itemClick
    }

    fun setOnItemLongClickListener(itemLongClick: (Model) -> Boolean) {
        this.itemLongClick = itemLongClick
    }

    abstract fun bindItem(binding: VDB, item: Model)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Model, VDB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResource, parent, false)
        return BaseHolder(binding, itemId, itemClick, itemLongClick, this::bindItem)
    }

    override fun onBindViewHolder(holder: BaseHolder<Model, VDB>, position: Int) {
        currentList?.let { holder.bind(currentList!![position]) }
        holder.itemView.isLongClickable = true
    }

    override fun getItemCount() = currentList?.let { currentList!!.size } ?: run { 0 }
}
