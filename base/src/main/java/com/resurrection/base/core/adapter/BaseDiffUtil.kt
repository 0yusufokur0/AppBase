package com.resurrection.base.core.adapter

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == newList[newPosition]
    override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == newList[newPosition]
}