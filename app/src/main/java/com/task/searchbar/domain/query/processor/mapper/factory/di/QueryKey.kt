package com.task.searchbar.domain.query.processor.mapper.factory.di

import com.task.searchbar.domain.query.processor.QueryType
import dagger.MapKey

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class QueryKey(val value: QueryType)
