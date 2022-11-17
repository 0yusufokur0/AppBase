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
import dagger.hilt.android.internal.managers.ViewComponentManager
import java.util.*

abstract class MultipleTypeAdapter<Model : Any> : RecyclerView.Adapter<BaseViewHolder<Model, ViewDataBinding>>() {

    private var currentList: ArrayList<Model> = arrayListOf()
    private var layoutResource: Int = 0
    private var itemClick: ((Model) -> Unit)? = null
    private var itemLongClick: ((Model) -> Boolean)? = null
    private lateinit var binding: ViewDataBinding
    private var itemId: Int? = null

    abstract fun getlayoutResourceAndBrId(item: Model): Pair<Int, Int?>
    abstract fun getDiffUtilCallback(oldList: List<Model>, newList: List<Model>): DiffUtil.Callback?
    abstract fun bindItem(binding: ViewDataBinding, item: Model)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Model, ViewDataBinding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResource, parent, false)
        return BaseViewHolder(binding, itemId, this::bindItem, itemClick, itemLongClick)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Model, ViewDataBinding>, position: Int) {
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

    fun getActivityByView(): Activity {
        val context = binding.root.context
        val activity = when (context) {
            is ViewComponentManager.FragmentContextWrapper -> context.baseContext
            else -> context
        }
        return activity as Activity
    }

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
}