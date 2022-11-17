package com.resurrection.appbase.ui.main.adapter

import com.resurrection.appbase.BR
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.sample.SampleChildItemModel
import com.resurrection.appbase.databinding.SampleChildItemBinding
import com.resurrection.base.core.adapter.BaseAdapter

class SampleChildRecyclerViewAdapter : BaseAdapter<SampleChildItemModel, SampleChildItemBinding>(
    layoutResource = R.layout.sample_child_item,
    itemId = BR.childItem
) {

    override fun bindItem(binding: SampleChildItemBinding, item: SampleChildItemModel) {
        binding.childItemButton.setOnClickListener {
            // activity.findNavController(R.id.mainFragmentContainerView).navigate(item.navigationDirections)
        }
    }
}