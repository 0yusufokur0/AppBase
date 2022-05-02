package com.resurrection.base.extensions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.base.core.adapter.BaseAdapter

fun RecyclerView.setGridLayoutManager(spanCount:Int) =
    GridLayoutManager(this.context, spanCount).also { this.layoutManager = it }

fun RecyclerView.setVerticalLinearLayoutManager(reverseLayout:Boolean = false) =
    LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, reverseLayout).also { this.layoutManager = it }

fun RecyclerView.setHorizontalLinearLayoutManager(reverseLayout:Boolean = false) =
    LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, reverseLayout).also { this.layoutManager = it }

fun <T> RecyclerView.init(adapter: BaseAdapter<T>,layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)){
    this.layoutManager = layoutManager
    this.adapter = adapter
}