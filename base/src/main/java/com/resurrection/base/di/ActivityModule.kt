package com.resurrection.base.di

import android.content.Context
import com.resurrection.base.components.widget.alertdialog.AlertDialogManager
import com.resurrection.base.components.widget.alertdialog.AlertDialogManagerImpl
import com.resurrection.base.components.widget.loadingindicator.LoadingIndicator
import com.resurrection.base.components.widget.loadingindicator.LoadingIndicatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    @ActivityScoped
     fun provideLoadingIndicator(@ActivityContext context: Context) : LoadingIndicator = LoadingIndicatorImpl(context)

    @Provides
    @ActivityScoped
     fun provideAlertDialogManager(@ActivityContext context: Context) : AlertDialogManager = AlertDialogManagerImpl(context)

}
