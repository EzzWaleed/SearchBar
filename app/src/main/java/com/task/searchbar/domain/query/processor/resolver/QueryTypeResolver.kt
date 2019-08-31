package com.task.searchbar.domain.query.processor.resolver

import com.task.searchbar.domain.query.processor.QueryType

abstract class QueryTypeResolver {
    internal var nextQueryTypeResolver: QueryTypeResolver? = null
    abstract fun resolveType(query: String): QueryType
}
