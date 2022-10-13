package com.resurrection.base.extensions.delegated.viewmodel

import androidx.lifecycle.*
import com.resurrection.base.components.lifecycle.util.activityComponent
import com.resurrection.base.components.lifecycle.util.fragmentComponent
import com.resurrection.base.core.activity.LifecycleActivity
import com.resurrection.base.core.fragment.LifecycleFragment
import com.resurrection.base.core.viewmodel.BaseViewModel

inline fun <VM : BaseViewModel> LifecycleActivity.viewModel(
    viewModelClass: Class<VM>,
    crossinline storeProducer: () -> ViewModelStore = { viewModelStore },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = { defaultViewModelProviderFactory }
) = activityComponent { ViewModelProvider(storeProducer(), factoryProducer())[viewModelClass] }

inline fun <VM : BaseViewModel> LifecycleFragment.viewModel(
    viewModelClass: Class<VM>,
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = {
        (this as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory ?: defaultViewModelProviderFactory
    }
) = fragmentComponent { ViewModelProvider(ownerProducer(), factoryProducer())[viewModelClass] }

inline fun <VM : BaseViewModel> LifecycleFragment.activityViewModel(
    viewModelClass: Class<VM>,
    crossinline ownerProducer: () -> ViewModelStore = { requireActivity().viewModelStore },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = { requireActivity().defaultViewModelProviderFactory }
) = fragmentComponent { ViewModelProvider(ownerProducer(), factoryProducer())[viewModelClass] }
