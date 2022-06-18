package com.resurrection.base.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.base.BR
import com.resurrection.base.R
import com.resurrection.base.core.adapter.BaseAdapter
import com.resurrection.base.databinding.ListVerticalItemBinding
import com.resurrection.base.models.Item

class AppListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.recyclerViewStyle
) : RecyclerView(context, attrs, defStyleAttr) {

    var mAdapter: ItemAdapter? = null

    fun init(setVertical: Boolean = true, list: ArrayList<String>, listener: (String) -> Unit) {
        val layoutRes = R.layout.list_vertical_item
        val itemList = arrayListOf<Item>()
        list.forEach { itemList.add(Item(it)) }
        layoutManager = if(setVertical) LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        else LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        mAdapter = ItemAdapter(setVertical)
        mAdapter?.setOnItemClickListener {
            listener(it.value)
        }
        adapter = mAdapter
    }

    fun setItems(items: ArrayList<String>) {
        val itemList = arrayListOf<Item>()
        items.forEach { itemList.add(Item(it)) }
        mAdapter?.addAll(itemList)
    }

    class ItemAdapter(val setVertical: Boolean = true): BaseAdapter<Item,ListVerticalItemBinding>(
        layoutResource = R.layout.list_vertical_item,
        itemId = BR.data,
        currentList = arrayListOf(),
    ) {
        override fun bindItem(binding: ListVerticalItemBinding, item: Item) {
            if (setVertical){
                binding.appTextViewHorizontal.visibility = GONE
            }
        }
    }
}