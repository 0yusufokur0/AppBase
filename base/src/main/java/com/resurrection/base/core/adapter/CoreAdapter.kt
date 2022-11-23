package com.resurrection.base.core.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.internal.managers.ViewComponentManager.FragmentContextWrapper
import java.util.*
import kotlin.collections.ArrayList

abstract class CoreAdapter<Model : Any, VDB : ViewDataBinding>(
    private val layoutResource: Int,
    private val itemId: Int?,
    protected var currentList: ArrayList<Model> = arrayListOf(),
) : RecyclerView.Adapter<BaseViewHolder<Model, VDB>>() {

    private var itemClick: ((Model) -> Unit)? = null
    private var itemLongClick: ((Model) -> Boolean)? = null
    private lateinit var binding: VDB

    abstract fun getDiffUtilCallback(oldList: List<Model>, newList: List<Model>): DiffUtil.Callback?
    abstract fun bindItem(binding: VDB, item: Model)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Model, VDB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResource, parent, false)
        return BaseViewHolder(binding, itemId, this::bindItem, itemClick, itemLongClick)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Model, VDB>, position: Int) {
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

    private fun getActivityByView(): Activity {
        val context = binding.root.context
        val activity = when (context) {
            is FragmentContextWrapper -> context.baseContext
            else -> context
        }
        return activity as Activity
    }

    // region list operations
    open fun addAll(list: List<Model>) {
        val diffUtil = getDiffUtilCallback(currentList, list) ?: run {
            BaseDiffUtil<Model>(currentList, list)
        }
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        currentList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    open fun add(item: Model) {
        currentList.add(item)
        notifyItemInserted(currentList.size - 1)
    }

    open fun add(item: Model,position: Int) {
        currentList.add(item)
        notifyItemInserted(position)
    }

    open fun remove(position: Int) {
        currentList.removeAt(position)
        notifyItemRemoved(position)
    }

    open fun remove(item: Model) {
        val position = currentList.indexOf(item)
        currentList.remove(item)
        notifyItemRemoved(position)
    }

    open fun update(item: Model) {
        val position = currentList.indexOf(item)
        currentList[position] = item
        notifyItemChanged(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun clear() {
        currentList.clear()
        notifyDataSetChanged()
    }

    open fun getItem(position: Int) = currentList[position]

    open fun getItems() = currentList.toList()

    open fun getItems(predicate: (Model) -> Boolean) = currentList.filter(predicate)

    open fun <R : Comparable<R>> sort(selector: (Model) -> R?) {
        val mutable = currentList.toMutableList()
        mutable.sortBy { selector(it) }
        addAll(mutable.toList() as java.util.ArrayList<Model>)
    }

    open fun <R> filterBy(constraint: CharSequence, selector: ((Model) -> R?)? = null) {
        val mFilter = object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = java.util.ArrayList<Model>()
                if (constraint == null || constraint.isEmpty()) filteredList.addAll(currentList)
                else {
                    val filterPattern =
                        constraint.toString().lowercase(Locale.getDefault()).trim()
                    selector?.let {
                        for (item in currentList)
                            if (it.invoke(item).toString().lowercase(Locale.getDefault())
                                .contains(filterPattern)
                            )
                                filteredList.add(item)
                    } ?: run {
                        for (item in currentList)
                            if (item.toString().lowercase(Locale.getDefault())
                                .contains(filterPattern)
                            )
                                filteredList.add(item)
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                currentList
            }
        }
        mFilter.filter(constraint)
    }

    // endregion
}
