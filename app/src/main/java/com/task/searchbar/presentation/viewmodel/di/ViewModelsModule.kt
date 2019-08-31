package com.task.searchbar.presentation.viewmodel.di

import androidx.lifecycle.ViewModel

import com.task.searchbar.presentation.viewmodel.MainViewModel
import com.task.searchbar.presentation.viewmodel.viewmodel_factory.di.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel
}
