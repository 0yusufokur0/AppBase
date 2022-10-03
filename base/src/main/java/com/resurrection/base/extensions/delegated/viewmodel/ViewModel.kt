package com.resurrection.base.extensions.delegated.viewmodel

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.*
import com.resurrection.base.components.lifecycle.activity.ActivityLifecycleAwareLazyComponent
import com.resurrection.base.components.lifecycle.fragment.FragmentLifecycleAwareLazyComponent
import com.resurrection.base.core.activity.LifecycleActivity
import com.resurrection.base.core.fragment.LifecycleFragment
import com.resurrection.base.core.viewmodel.BaseViewModel

inline fun <VM : BaseViewModel> LifecycleActivity.viewModel(
    viewModelClass: Class<VM>,
    crossinline storeProducer: () -> ViewModelStore = { viewModelStore },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = { defaultViewModelProviderFactory }
) = ActivityLifecycleAwareLazyComponent(
    lifecycleActivity = this,
    instanceCreator = { ViewModelProvider(storeProducer(), factoryProducer())[viewModelClass] }
)

inline fun <VM : BaseViewModel> LifecycleFragment.viewModel(
    viewModelClass: Class<VM>,
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = {
        (this as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory ?: defaultViewModelProviderFactory
    }
) = FragmentLifecycleAwareLazyComponent(
    lifecycleFragment = this,
    instanceCreator = { ViewModelProvider(ownerProducer(), factoryProducer())[viewModelClass] }
)

inline fun <VM : BaseViewModel> LifecycleFragment.activityViewModel(
    viewModelClass: Class<VM>,
    crossinline ownerProducer: () -> ViewModelStore = { requireActivity().viewModelStore },
    crossinline factoryProducer: (() -> ViewModelProvider.Factory) = { requireActivity().defaultViewModelProviderFactory }
) = FragmentLifecycleAwareLazyComponent(
    lifecycleFragment = this,
    instanceCreator = { ViewModelProvider(ownerProducer(), factoryProducer())[viewModelClass] }
)
