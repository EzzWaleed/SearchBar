package com.task.searchbar.data.mapper

import com.task.searchbar.data.mapper.base.EntityMapper
import com.task.searchbar.data.mapper.base.ResourceMapper
import com.task.searchbar.data.remote.ApiServices
import javax.inject.Inject

class SuggestionsMapper @Inject constructor(
    apiServices: ApiServices,
    resourceMapper: ResourceMapper<List<Any>, List<String>>
) : EntityMapper<List<Any>, List<String>>() {
    override fun map(input: List<Any>): List<String> {
        return input[2] as List<String>
    }
}