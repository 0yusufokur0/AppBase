package com.resurrection.appbase.di.components.viewmodel

import dagger.hilt.DefineComponent
import dagger.hilt.android.components.ViewModelComponent

@UseCaseViewModelScope
@DefineComponent(parent = ViewModelComponent::class)
interface UseCaseViewModelComponent {
    @DefineComponent.Builder
    interface Factory {
        fun create(): UseCaseViewModelComponent
    }
}
