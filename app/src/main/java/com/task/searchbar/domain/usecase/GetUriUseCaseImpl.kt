package com.task.searchbar.domain.usecase

import android.net.Uri
import com.task.searchbar.domain.query.processor.mapper.factory.QueryMapperFactory
import com.task.searchbar.domain.query.processor.resolver.QueryResolverChainBuilder
import com.task.searchbar.domain.query.processor.resolver.impl.EmailResolver
import com.task.searchbar.domain.query.processor.resolver.impl.FaceBookResolver
import com.task.searchbar.domain.query.processor.resolver.impl.LinkResolver
import com.task.searchbar.domain.query.processor.resolver.impl.SearchResolver
import com.task.searchbar.domain.query.processor.validator.LinkValidator
import java.util.*
import javax.inject.Inject

class GetUriUseCaseImpl @Inject constructor(
    private val queryMapperFactory: QueryMapperFactory,
    private val linkValidator: LinkValidator
) : GetUriUseCase {
    override fun getUri(query: String): Uri {

        val temp = query.toLowerCase(Locale.getDefault()).trim()

        val queryTypeResolver = QueryResolverChainBuilder(SearchResolver())
            .addChain(EmailResolver(linkValidator))
            .addChain(FaceBookResolver(linkValidator))
            .addChain(LinkResolver(linkValidator))
            .build()

        val queryType = queryTypeResolver.resolveType(temp)

        return queryMapperFactory.getMapper(queryType).map(temp)
    }
}