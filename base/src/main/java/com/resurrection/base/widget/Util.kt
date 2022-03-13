package com.resurrection.base.widget

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setGridLayoutManager(spanCount:Int) =
    GridLayoutManager(this.context, spanCount).also { this.layoutManager = it }

fun RecyclerView.setVerticalLinearLayoutManager(reverseLayout:Boolean = false) =
    LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, reverseLayout)

fun RecyclerView.setHorizontalLinearLayoutManager(reverseLayout:Boolean = false) =
    LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, reverseLayout)
