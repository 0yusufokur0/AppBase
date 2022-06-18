package com.resurrection.base.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.base.core.adapter.BaseLoadStateAdapter.BaseLoadStateViewHolder

abstract class BaseLoadStateAdapter<T : ViewDataBinding>(private val layoutResource: Int) :
    LoadStateAdapter<BaseLoadStateViewHolder<T>>() {

    abstract fun bindLoadState(binding: T, loadState: LoadState)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BaseLoadStateViewHolder<T> =
        BaseLoadStateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutResource,
                parent,
                false
            ),
            this::bindLoadState
        )

    override fun onBindViewHolder(holder: BaseLoadStateViewHolder<T>, loadState: LoadState) =
        holder.bind(loadState)


    class BaseLoadStateViewHolder<T : ViewDataBinding>(
        private val binding: T,
        private val bindLoadState: ((binding: T, loadState: LoadState) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            bindLoadState?.invoke(binding, loadState)
        }
    }


}