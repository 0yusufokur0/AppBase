package com.resurrection.appbase.ui.main.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import coil.loadAny
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.ImageItemBinding
import com.resurrection.appbase.databinding.StringItemBinding
import com.resurrection.base.core.adapter.MultipleTypeAdapter

class MultipleAdapterTest : MultipleTypeAdapter<String>() {

    override fun getlayoutResourceAndBrId(item: String): Pair<Int, Int?> {
        return if (item.contains("http")) {
            Pair(R.layout.image_item, null)
        } else {
            Pair(R.layout.string_item, null)
        }
    }

    override fun getDiffUtilCallback(oldList: List<String>, newList: List<String>): DiffUtil.Callback? = null

    override fun bindItem(binding: ViewDataBinding, item: String) {
        when (binding) {
            is ImageItemBinding -> {
                binding.iamgeView.loadAny(item)
            }
            is StringItemBinding -> {
                binding.textView.text = item
            }
        }
    }
}