package com.task.searchbar.domain.query.processor.resolver

import java.util.*

class QueryResolverChainBuilder internal constructor(private val defaultChainLink: QueryTypeResolver) {
    private val chainLinks = ArrayList<QueryTypeResolver>()

    fun addChain(chainLink: QueryTypeResolver): QueryResolverChainBuilder {
        chainLinks.add(chainLink)
        return this
    }

    private fun setupChainsSequence() {
        if (chainLinks.isNotEmpty()) {
            for (i in 0 until chainLinks.size) {
                if (i < chainLinks.size - 1) {
                    val chainLink = chainLinks[i]
                    chainLink.nextQueryTypeResolver = (chainLinks[i + 1])
                } else
                    chainLinks[i].nextQueryTypeResolver = defaultChainLink
            }
        } else
            chainLinks.add(defaultChainLink)
    }

    fun build(): QueryTypeResolver {
        setupChainsSequence()
        return chainLinks.first()
    }
}