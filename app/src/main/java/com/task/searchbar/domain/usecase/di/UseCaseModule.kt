package com.task.searchbar.domain.usecase.di

import com.task.searchbar.domain.query.processor.mapper.factory.di.MapperFactoryModule
import com.task.searchbar.domain.query.processor.validator.di.ValidatorModule
import com.task.searchbar.domain.usecase.GetUriUseCase
import com.task.searchbar.domain.usecase.GetUriUseCaseImpl
import com.task.searchbar.domain.usecase.SuggestUseCase
import com.task.searchbar.domain.usecase.SuggestUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [MapperFactoryModule::class, ValidatorModule::class])
interface UseCaseModule {
    @Binds
    fun bindsGetUriUseCase(getUriUseCaseImpl: GetUriUseCaseImpl): GetUriUseCase

    @Binds
    fun bindsSuggestUseCase(suggestUseCaseImpl: SuggestUseCaseImpl): SuggestUseCase
}