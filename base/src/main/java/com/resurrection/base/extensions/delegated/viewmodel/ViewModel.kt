package com.resurrection.base.extensions.delegated.viewmodel

import androidx.lifecycle.*
import com.resurrection.base.components.lifecycle.util.activityComponent
import com.resurrection.base.components.lifecycle.util.fragmentComponent
import com.resurrection.base.core.activity.CoreActivity
import com.resurrection.base.core.fragment.CoreFragment
import com.resurrection.base.core.viewmodel.BaseViewModel

inline fun <reified VM : BaseViewModel> CoreActivity.viewModel(
    crossinline storeProducer: () -> ViewModelStore = { viewModelStore },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = { defaultViewModelProviderFactory }
) = activityComponent { ViewModelProvider(storeProducer(), factoryProducer())[VM::class.java] }

inline fun <reified VM : BaseViewModel> CoreFragment.viewModel(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = {
        (this as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory ?: defaultViewModelProviderFactory
    }
) = fragmentComponent { ViewModelProvider(ownerProducer(), factoryProducer())[VM::class.java] }

inline fun <reified VM : BaseViewModel> CoreFragment.activityViewModel(
    crossinline ownerProducer: () -> ViewModelStore = { requireActivity().viewModelStore },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = { requireActivity().defaultViewModelProviderFactory }
) = fragmentComponent { ViewModelProvider(ownerProducer(), factoryProducer())[VM::class.java] }
