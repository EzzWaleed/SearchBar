package com.task.searchbar.data.di

import android.content.Context
import com.task.searchbar.data.repo.SuggestsRepo
import com.task.searchbar.data.repo.di.RepoModule

import dagger.BindsInstance
import dagger.Component

@Component(modules = [RepoModule::class])
@DataScope
interface DataComponent {

    fun exposeSuggestRepo(): SuggestsRepo

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): DataComponent
    }
}
