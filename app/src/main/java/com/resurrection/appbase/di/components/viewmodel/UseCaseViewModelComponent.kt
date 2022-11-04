package com.resurrection.appbase.di.components.viewmodel

import com.resurrection.appbase.di.components.usecase.UseCaseComponent
import dagger.hilt.DefineComponent

@UseCaseViewModelScope
@DefineComponent(parent = UseCaseComponent::class)
interface UseCaseViewModelComponent {
    @DefineComponent.Builder
    interface Factory {
        fun create(): UseCaseViewModelComponent
    }
}
