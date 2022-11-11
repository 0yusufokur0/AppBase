package com.resurrection.appbase.ui.main.adapter

import android.animation.LayoutTransition
import com.resurrection.appbase.BR
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.sample.SampleModel
import com.resurrection.appbase.databinding.ItemSampleBinding
import com.resurrection.base.core.adapter.BaseAdapter
import com.resurrection.base.extensions.setHorizontalLinearLayoutManager

class SamplesRecyclerViewAdapter : BaseAdapter<SampleModel, ItemSampleBinding>(
    layoutResource = R.layout.item_sample,
    itemId = BR.sampleItem
) {
    private var lastExpandedItemBinding: ItemSampleBinding? = null
    private var lastExpandedItemModel: SampleModel? = null
    override fun bindItem(binding: ItemSampleBinding, item: SampleModel) = with(binding) {
        binding.title.text = item.title
        childItemRecyclerView.adapter = SampleChildRecyclerViewAdapter().apply {
            addAll(item.examples)
        }
        childItemRecyclerView.setHorizontalLinearLayoutManager()
        applyLayoutTransition(binding)
        appLinearLayout.setOnClickListener {
            if (item.isExpanded) { // collapse
                description.maxLines = 2
            } else { // expand
                lastExpandedItemBinding?.description?.maxLines = 2 // change expanded last item state
                lastExpandedItemModel?.isExpanded = false
                description.maxLines = 5
                lastExpandedItemBinding = this // set last expanded item
                lastExpandedItemModel = item
            }
        }
    }

    private fun applyLayoutTransition(binding: ItemSampleBinding) {
        binding.appLinearLayout.layoutTransition = LayoutTransition().apply {
            setDuration(500)
            enableTransitionType(LayoutTransition.CHANGING)
        }
    }
}