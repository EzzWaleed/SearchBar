package com.task.searchbar.domain.query.processor.resolver.impl

import com.task.searchbar.domain.query.processor.QueryType
import com.task.searchbar.domain.query.processor.resolver.QueryTypeResolver
import com.task.searchbar.domain.query.processor.validator.LinkValidator

class FaceBookResolver constructor(private val linkValidator: LinkValidator) :
    QueryTypeResolver() {

    override fun resolveType(query: String): QueryType {
        return if (linkValidator.isFacebookLink(query))
            QueryType.FACEBOOK
        else
            nextQueryTypeResolver!!.resolveType(query)
    }
}