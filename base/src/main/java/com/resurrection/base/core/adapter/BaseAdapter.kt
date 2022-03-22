package com.resurrection.base.core.adapter

import android.annotation.SuppressLint
import android.widget.Filter
import androidx.recyclerview.widget.DiffUtil
import java.util.*

open class BaseAdapter<T>(
    private val layoutResource: Int,
    private val itemId: Int? = null,
    private val currentList: ArrayList<T>? = arrayListOf(),
    private val onItemClick: ((T) -> Unit)? = null
) : CoreAdapter<T>(layoutResource, itemId, currentList, onItemClick) {

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(list: List<T>) = currentList?.let {
        val diffUtil = BaseDiffUtil(currentList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        currentList.clear()
        currentList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }?: run {
        currentList?.addAll(list)
        notifyDataSetChanged()
    }

    fun add(item: T) = currentList?.let {
        currentList.add(item)
        notifyItemInserted(currentList.size - 1)
    }?: run {
        currentList?.add(item)
        notifyItemInserted(0)
    }

    fun remove(item: T) = currentList?.let {
        val position = currentList.indexOf(item)
        currentList.remove(item)
        notifyItemRemoved(position)
    }

    fun update(item: T) = currentList?.let {
        val position = currentList.indexOf(item)
        currentList[position] = item
        notifyItemChanged(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        currentList?.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = currentList?.let { currentList[position] }

    fun getItems() = currentList?.let { currentList.toList() }

    fun getItems(predicate: (T) -> Boolean) = currentList?.let { currentList.filter(predicate) }

    fun <R> filterBy(constraint: CharSequence, selector: ((T) -> R?)? = null) = currentList?.let {
        val mFilter = object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<T>()
                if (constraint == null || constraint.isEmpty()) filteredList.addAll(currentList)
                else {
                    val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim()
                    selector?.let {
                        for (item in currentList)
                            if (it.invoke(item).toString().lowercase(Locale.getDefault()).contains(filterPattern))
                                filteredList.add(item)
                    } ?: run {
                        for (item in currentList)
                            if (item.toString().lowercase(Locale.getDefault()).contains(filterPattern))
                                filteredList.add(item)
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                currentList.clear()
                currentList.addAll(results?.values as ArrayList<T>)
                notifyDataSetChanged()
            }
        }
        mFilter.filter(constraint)
    }

    fun <R : Comparable<R>> sort(selector: (T) -> R?) {
        val mutable = currentList?.toMutableList()
        mutable?.sortBy { selector(it) }
        addAll(mutable?.toList() as ArrayList<T>)
    }

}