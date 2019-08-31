package com.task.searchbar.domain.di

import android.content.Context
import com.task.searchbar.domain.usecase.GetUriUseCase
import com.task.searchbar.domain.usecase.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [UseCaseModule::class])
@DomainScope
interface DomainComponent {
    fun getUriUscase(): GetUriUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindsContext(context: Context): Builder

        fun build(): DomainComponent
    }
}