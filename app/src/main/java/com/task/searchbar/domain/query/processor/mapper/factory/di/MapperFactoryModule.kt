package com.task.searchbar.domain.query.processor.mapper.factory.di

import com.task.searchbar.domain.di.DomainScope
import com.task.searchbar.domain.query.processor.mapper.di.MapperModule
import com.task.searchbar.domain.query.processor.mapper.factory.QueryMapperFactory
import com.task.searchbar.domain.query.processor.mapper.factory.QueryMapperFactoryImpl

import dagger.Binds
import dagger.Module

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Module(includes = [MapperModule::class])
interface MapperFactoryModule {
    @Binds
    @DomainScope
    fun bindsFactory(queryMapperFactory: QueryMapperFactoryImpl): QueryMapperFactory
}
