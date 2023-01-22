package com.resurrection.base.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class MultipleTypeAdapter<Model : Any> : RecyclerView.Adapter<CoreViewHolder<Model, ViewDataBinding>>() {

    protected var currentList: ArrayList<Model> = arrayListOf()
    private var layoutResource: Int = 0
    private var itemClick: ((Model) -> Unit)? = null
    private var itemLongClick: ((Model) -> Boolean)? = null
    private lateinit var binding: ViewDataBinding
    private var itemId: Int? = null

    abstract fun getlayoutResourceAndBrId(item: Model): Pair<Int, Int?>
    abstract fun bindItem(binding: ViewDataBinding, item: Model)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoreViewHolder<Model, ViewDataBinding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResource, parent, false)
        return CoreViewHolder(binding, itemId, this::bindItem, itemClick, itemLongClick)
    }

    override fun onBindViewHolder(holder: CoreViewHolder<Model, ViewDataBinding>, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.isLongClickable = true
    }

    override fun getItemViewType(position: Int): Int {
        val pair = getlayoutResourceAndBrId(currentList.get(position))
        layoutResource = pair.first
        itemId = pair.second
        return position
    }

    override fun getItemCount() = currentList.size

    open fun setOnItemClickListener(itemClick: (Model) -> Unit) {
        this.itemClick = itemClick
    }

    open fun setOnItemLongClickListener(itemLongClick: (Model) -> Boolean) {
        this.itemLongClick = itemLongClick
    }
}