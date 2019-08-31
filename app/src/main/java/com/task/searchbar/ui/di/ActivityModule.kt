package com.task.searchbar.ui.di

import com.task.searchbar.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}