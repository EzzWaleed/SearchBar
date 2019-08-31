package com.task.searchbar.presentation.viewmodel.viewmodel_factory.di

import androidx.lifecycle.ViewModelProvider

import com.task.searchbar.presentation.di.PresentationScope
import com.task.searchbar.presentation.viewmodel.di.ViewModelsModule
import com.task.searchbar.presentation.viewmodel.viewmodel_factory.ViewModelFactory

import dagger.Binds
import dagger.Module

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Module(includes = [ViewModelsModule::class, SchedulersModule::class])
interface ViewModelFactoryModule {
    @Binds
    @PresentationScope
    fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
