package com.task.searchbar.app.di

import com.task.searchbar.app.App
import com.task.searchbar.presentation.di.PresentationComponent
import com.task.searchbar.ui.di.ActivityModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    dependencies = [PresentationComponent::class],
    modules = [ActivityModule::class, AndroidSupportInjectionModule::class]
)
@AppScope
interface AppComponent {
    fun inject(app: App)
}