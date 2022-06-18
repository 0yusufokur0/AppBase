package com.resurrection.base.core.adapter

import android.annotation.SuppressLint
import android.widget.Filter
import androidx.databinding.ViewDataBinding
import androidx.databinding.ViewDataBindingKtx
import androidx.recyclerview.widget.DiffUtil
import java.util.*

abstract class BaseAdapter<Model:Any,VDB: ViewDataBinding>(
    private var layoutResource: Int,
    private var itemId: Int? = null,
    private var currentList: ArrayList<Model>? = arrayListOf(),
) : CoreAdapter<Model,VDB>(layoutResource, itemId, currentList) {

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(list: List<Model>) = currentList?.let {
        val diffUtil = BaseDiffUtil(currentList!!, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        currentList!!.clear()
        currentList!!.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }?: run {
        currentList?.addAll(list)
        notifyDataSetChanged()
    }

    fun add(item: Model) = currentList?.let {
        currentList!!.add(item)
        notifyItemInserted(currentList!!.size - 1)
    }?: run {
        currentList?.add(item)
        notifyItemInserted(0)
    }

    fun remove(item: Model) = currentList?.let {
        val position = currentList!!.indexOf(item)
        currentList!!.remove(item)
        notifyItemRemoved(position)
    }

    fun update(item: Model) = currentList?.let {
        val position = currentList!!.indexOf(item)
        currentList!![position] = item
        notifyItemChanged(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        currentList?.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = currentList?.let { currentList!![position] }

    fun getItems() = currentList?.let { currentList!!.toList() }

    fun getItems(predicate: (Model) -> Boolean) = currentList?.let { currentList!!.filter(predicate) }

    fun <R> filterBy(constraint: CharSequence, selector: ((Model) -> R?)? = null) = currentList?.let {
        val mFilter = object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<Model>()
                if (constraint == null || constraint.isEmpty()) filteredList.addAll(currentList!!)
                else {
                    val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim()
                    selector?.let {
                        for (item in currentList!!)
                            if (it.invoke(item).toString().lowercase(Locale.getDefault()).contains(filterPattern))
                                filteredList.add(item)
                    } ?: run {
                        for (item in currentList!!)
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
                currentList?: run {
                currentList!!.clear()
                currentList!!.addAll(results?.values as ArrayList<Model>)
                notifyDataSetChanged()
                }
            }
        }
        mFilter.filter(constraint)
    }

    fun <R : Comparable<R>> sort(selector: (Model) -> R?) {
        val mutable = currentList?.toMutableList()
        mutable?.sortBy { selector(it) }
        addAll(mutable?.toList() as ArrayList<Model>)
    }

}