package com.resurrection.base.core.adapter

import android.annotation.SuppressLint
import android.widget.Filter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import java.util.*

abstract class BaseAdapter<Model : Any, VDB : ViewDataBinding>(
    layoutResource: Int,
    itemId: Int? = null,
    private val currentList: ArrayList<Model> = arrayListOf(),
) : CoreAdapter<Model, VDB>(layoutResource, itemId, currentList) {


    open fun addAll(list: List<Model>) {
        val diffUtil = BaseDiffUtil(currentList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        currentList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    open fun add(item: Model) {
        currentList.add(item)
        notifyItemInserted(currentList.size - 1)
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
        addAll(mutable.toList() as ArrayList<Model>)
    }

    open fun <R> filterBy(constraint: CharSequence, selector: ((Model) -> R?)? = null) {
        val mFilter = object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<Model>()
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

}