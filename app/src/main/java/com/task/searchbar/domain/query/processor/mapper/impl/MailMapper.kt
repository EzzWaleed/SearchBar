package com.task.searchbar.domain.query.processor.mapper.impl

import android.net.Uri
import com.task.searchbar.domain.query.processor.mapper.QueryMapper
import javax.inject.Inject

class MailMapper @Inject constructor(): QueryMapper {
    override fun map(query: String): Uri {
        return Uri.parse(query)
    }
}