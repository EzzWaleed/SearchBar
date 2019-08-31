package com.task.searchbar.domain.query.processor.resolver.impl

import com.task.searchbar.domain.query.processor.QueryType
import com.task.searchbar.domain.query.processor.resolver.QueryTypeResolver
import com.task.searchbar.domain.query.processor.validator.LinkValidator

class LinkResolver constructor(private val linkValidator: LinkValidator) :
    QueryTypeResolver() {

    override fun resolveType(query: String): QueryType {
        return if (linkValidator.isLink(query))
            QueryType.LINK
        else
            nextQueryTypeResolver!!.resolveType(query)
    }
}