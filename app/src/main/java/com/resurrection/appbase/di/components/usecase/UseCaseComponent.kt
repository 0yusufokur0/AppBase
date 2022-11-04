package com.resurrection.appbase.di.components.usecase

import dagger.hilt.DefineComponent
import dagger.hilt.android.components.ActivityRetainedComponent

@UseCaseScope
@DefineComponent(parent = ActivityRetainedComponent::class)
interface UseCaseComponent {
    @DefineComponent.Builder
    interface Factory {
        fun create(): UseCaseComponent
    }
}
