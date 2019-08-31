package com.task.searchbar.domain.query.processor.resolver.impl

import com.task.searchbar.domain.query.processor.QueryType
import com.task.searchbar.domain.query.processor.resolver.QueryTypeResolver

class SearchResolver : QueryTypeResolver() {
    override fun resolveType(query: String): QueryType {
        return QueryType.SEARCH
    }
}