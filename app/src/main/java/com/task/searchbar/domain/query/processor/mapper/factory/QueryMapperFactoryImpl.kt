package com.task.searchbar.domain.query.processor.mapper.factory

import com.task.searchbar.domain.query.processor.QueryType
import com.task.searchbar.domain.query.processor.mapper.QueryMapper
import javax.inject.Inject
import javax.inject.Provider

class QueryMapperFactoryImpl @Inject constructor(private val map: MutableMap<QueryType, Provider<QueryMapper>>) :
    QueryMapperFactory {
    override fun getMapper(queryType: QueryType): QueryMapper {
        return map.getValue(queryType).get()
    }
}