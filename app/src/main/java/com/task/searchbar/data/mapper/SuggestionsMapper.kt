package com.task.searchbar.data.mapper

import com.task.searchbar.data.mapper.base.EntityMapper
import javax.inject.Inject

class SuggestionsMapper @Inject constructor() : EntityMapper<List<Any>, List<String>>() {
    override fun map(input: List<Any>): List<String> {
        return input[1] as MutableList<String>
    }
}