package com.task.searchbar.data.repo.di

import com.task.searchbar.data.mapper.di.DataMapperModule
import com.task.searchbar.data.remote.client.di.ClientModule
import com.task.searchbar.data.repo.SuggestsRepo
import com.task.searchbar.data.repo.SuggestsRepoImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DataMapperModule::class, ClientModule::class])
interface RepoModule {
    @Binds
    fun bindsSuggestRepo(suggestsRepoImpl: SuggestsRepoImpl): SuggestsRepo
}