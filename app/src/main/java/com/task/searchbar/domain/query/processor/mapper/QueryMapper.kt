package com.task.searchbar.domain.query.processor.mapper

import android.net.Uri

interface QueryMapper {
    fun map(query: String): Uri
}