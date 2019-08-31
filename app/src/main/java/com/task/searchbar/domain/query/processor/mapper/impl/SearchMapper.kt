package com.task.searchbar.domain.query.processor.mapper.impl

import android.net.Uri
import com.task.searchbar.domain.query.processor.mapper.QueryMapper
import javax.inject.Inject


class SearchMapper @Inject constructor() : QueryMapper {

    private val SEARCH_ENGINE_GOOGLE = "https://www.google.com/search?q="

    override fun map(query: String): Uri {
        return Uri.parse(SEARCH_ENGINE_GOOGLE + query)
    }
}