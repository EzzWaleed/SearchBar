package com.task.searchbar.domain.query.processor.mapper.impl

import android.net.Uri
import com.task.searchbar.domain.query.processor.SCHEMA.ABOUT
import com.task.searchbar.domain.query.processor.SCHEMA.HTTPS
import com.task.searchbar.domain.query.processor.mapper.QueryMapper
import javax.inject.Inject

class LinkMapper @Inject constructor() : QueryMapper {
    override fun map(query: String): Uri {
        return if (query.startsWith(ABOUT.value) || query.contains("://"))
            Uri.parse(query)
        else
            Uri.parse(HTTPS.value + query)
    }
}