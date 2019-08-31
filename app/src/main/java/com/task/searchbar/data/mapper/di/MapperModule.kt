package com.task.searchbar.data.mapper.di

import com.task.searchbar.data.mapper.SuggestionsMapper
import com.task.searchbar.data.mapper.base.EntityMapper
import dagger.Module

@Module
interface MapperModule {
    fun bindsSuggestionsMapper(suggestionsMapper: SuggestionsMapper): EntityMapper<List<Any>, List<String>>
}