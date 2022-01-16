package com.resurrection.base.core

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

open class BaseAdapter<T>(
    private val layoutResource: Int,
    private val  itemId: Int,
    private val currentList: ArrayList<T>,
    private val onItemClick: (T) -> Unit
) : RecyclerView.Adapter<BaseAdapter.BaseHolder<T>>(),Filterable {

    @Inject
    lateinit var appSession: AppSession // TODO: Logger on click item in recycler view

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
       var binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutResource, parent, false)
        return BaseHolder(binding, itemId, onItemClick)
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) { holder.bind(currentList[position]) }

    override fun getItemCount() = currentList.size

    class BaseHolder<T>(
        private var binding: ViewDataBinding,
        private var itemId: Int,
        var onItemClick: (T) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(itemId, item)
            itemView.setOnClickListener { onItemClick((item)) }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<T>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(currentList)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim()
                    for (item in currentList) {
                        if (item.toString().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                currentList.clear()
                currentList.addAll(results?.values as ArrayList<T>)
                notifyDataSetChanged()
            }
        }
    }

    class BaseDiffUtil<T>(
        private val oldList: List<T>,
        private val newList: List<T>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == newList[newPosition]
        override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == newList[newPosition]
    }

    fun addAll(list: List<T>) {
        val diffUtil = BaseDiffUtil(currentList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        currentList.clear()
        currentList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    fun add(item: T) {
        currentList.add(item)
        notifyItemInserted(currentList.size - 1)
    }

    fun remove(item: T) {
        val position = currentList.indexOf(item)
        currentList.remove(item)
        notifyItemRemoved(position)
    }

    fun update(item: T) {
        val position = currentList.indexOf(item)
        currentList[position] = item
        notifyItemChanged(position)
    }

    fun clear() {
        currentList.clear()
        notifyDataSetChanged()
    }


    fun <T, R : Comparable<R>> MutableList<T>.sort( selector: (T) -> R?): Unit {

    }




    fun getItem(position: Int) = currentList[position]
}

