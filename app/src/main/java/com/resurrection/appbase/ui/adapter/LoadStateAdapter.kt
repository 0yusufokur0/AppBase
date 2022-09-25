package com.resurrection.appbase.ui.adapter

import android.view.View
import androidx.paging.LoadState
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.ItemLoadingStateBinding
import com.resurrection.base.core.adapter.BaseLoadStateAdapter

class LoadStateAdapter(
    private val retry: () -> Unit
) : BaseLoadStateAdapter<ItemLoadingStateBinding>(R.layout.item_loading_state) {

    override fun bindLoadState(binding: ItemLoadingStateBinding, loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.textViewError.text = loadState.error.localizedMessage
        }

        binding.progressbar.visible(loadState is LoadState.Loading)
        binding.buttonRetry.visible(loadState is LoadState.Error)
        binding.textViewError.visible(loadState is LoadState.Error)
        binding.buttonRetry.setOnClickListener {
            retry()
        }

        binding.progressbar.visibility = View.VISIBLE
    }

    private fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

}