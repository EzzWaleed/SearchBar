package com.task.searchbar.domain.query.processor.mapper.factory

import com.task.searchbar.domain.query.processor.QueryType
import com.task.searchbar.domain.query.processor.mapper.QueryMapper

interface QueryMapperFactory {
    fun getMapper(queryType: QueryType): QueryMapper
}