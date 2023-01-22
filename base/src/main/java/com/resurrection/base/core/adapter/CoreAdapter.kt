package com.resurrection.base.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

abstract class CoreAdapter<Model : Any, VDB : ViewDataBinding>(
    private val layoutResource: Int,
    private val itemId: Int?,
    protected var currentList: ArrayList<Model> = arrayListOf(),
) : RecyclerView.Adapter<CoreViewHolder<Model, VDB>>() {

    private var itemClick: ((Model) -> Unit)? = null
    private var itemLongClick: ((Model) -> Boolean)? = null
    private lateinit var binding: VDB

    abstract fun bindItem(binding: VDB, item: Model)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder<Model, VDB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResource, parent, false)
        return CoreViewHolder(binding, itemId, this::bindItem, itemClick, itemLongClick)
    }

    override fun onBindViewHolder(holder: CoreViewHolder<Model, VDB>, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.isLongClickable = true
    }

    override fun getItemCount() = currentList.size

    open fun setOnItemClickListener(itemClick: (Model) -> Unit) {
        this.itemClick = itemClick
    }

    open fun setOnItemLongClickListener(itemLongClick: (Model) -> Boolean) {
        this.itemLongClick = itemLongClick
    }
}
