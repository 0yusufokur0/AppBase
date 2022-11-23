package com.resurrection.base.extensions.delegated.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.resurrection.base.components.lifecycle.util.activityComponent
import com.resurrection.base.components.lifecycle.util.fragmentComponent
import com.resurrection.base.core.viewmodel.CoreViewModel

inline fun <reified VM : CoreViewModel> AppCompatActivity.viewModel(
    crossinline storeProducer: () -> ViewModelStore = { viewModelStore },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = { defaultViewModelProviderFactory }
) = activityComponent { ViewModelProvider(storeProducer(), factoryProducer())[VM::class.java] }

inline fun <reified VM : CoreViewModel> Fragment.viewModel(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = {
        (this as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory ?: defaultViewModelProviderFactory
    }
) = fragmentComponent { ViewModelProvider(ownerProducer(), factoryProducer())[VM::class.java] }

inline fun <reified VM : CoreViewModel> Fragment.activityViewModel(
    crossinline ownerProducer: () -> ViewModelStore = { requireActivity().viewModelStore },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = { requireActivity().defaultViewModelProviderFactory }
) = fragmentComponent { ViewModelProvider(ownerProducer(), factoryProducer())[VM::class.java] }
