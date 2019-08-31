package com.task.searchbar.domain.di

import android.content.Context
import com.task.searchbar.data.di.DataComponent
import com.task.searchbar.domain.usecase.GetUriUseCase
import com.task.searchbar.domain.usecase.SuggestUseCase
import com.task.searchbar.domain.usecase.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [UseCaseModule::class],
    dependencies = [DataComponent::class]
)
@DomainScope
interface DomainComponent {

    fun exposeGetUriUseCase(): GetUriUseCase
    fun exposeSuggestUseCase(): SuggestUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindsContext(context: Context): Builder

        fun build(): DomainComponent
    }
}