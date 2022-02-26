package com.resurrection.base.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.base.BR
import com.resurrection.base.R
import com.resurrection.base.core.adapter.BaseAdapter
import com.resurrection.base.model.Item

class AppListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.recyclerViewStyle
) : RecyclerView(context, attrs, defStyleAttr) {

    var mAdapter: BaseAdapter<Item>? = null

    fun setUp(setVertical: Boolean = true, list: ArrayList<String>, listener: (String) -> Unit) {
        val layoutRes = if (!setVertical) R.layout.list_horizontal_item else R.layout.list_vertical_item
        val itemList = arrayListOf<Item>()
        list.forEach { itemList.add(Item(it)) }
        layoutManager = if(setVertical) LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        else LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        mAdapter = BaseAdapter(layoutRes, BR.data, itemList) { listener(it.value) }
        adapter = mAdapter
    }

    fun setItems(items: ArrayList<String>) {
        val itemList = arrayListOf<Item>()
        items.forEach { itemList.add(Item(it)) }
        mAdapter?.addAll(itemList)
    }
}