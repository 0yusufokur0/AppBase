package com.resurrection.base.core.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.internal.managers.ViewComponentManager.FragmentContextWrapper

abstract class CoreAdapter<Model : Any, VDB : ViewDataBinding>(
    private val layoutResource: Int,
    private val itemId: Int?,
    private var currentList: ArrayList<Model> = arrayListOf(),
) : RecyclerView.Adapter<BaseViewHolder<Model, VDB>>() {

    private var itemClick: ((Model) -> Unit)? = null
    private var itemLongClick: ((Model) -> Boolean)? = null
    private lateinit var binding: VDB
    lateinit var activity: Activity private set

    open fun setOnItemClickListener(itemClick: (Model) -> Unit) {
        this.itemClick = itemClick
    }

    open fun setOnItemLongClickListener(itemLongClick: (Model) -> Boolean) {
        this.itemLongClick = itemLongClick
    }

    abstract fun bindItem(binding: VDB, item: Model)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Model, VDB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResource, parent, false)
        activity = getActivityByView()
        return BaseViewHolder(binding, itemId, this::bindItem, itemClick, itemLongClick)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Model, VDB>, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.isLongClickable = true
    }

    override fun getItemCount() = currentList.size

    private fun getActivityByView(): Activity {
        val context = binding.root.context
        val activity = when (context) {
            is FragmentContextWrapper -> context.baseContext
            else -> context
        }
        return activity as Activity
    }
}
