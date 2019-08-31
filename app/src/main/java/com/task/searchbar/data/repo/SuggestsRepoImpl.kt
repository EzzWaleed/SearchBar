package com.task.searchbar.data.repo

import com.task.searchbar.data.mapper.SuggestionsMapper
import com.task.searchbar.data.mapper.base.ResourceMapper
import com.task.searchbar.data.remote.ApiServices
import com.task.searchbar.data.resource.Resource
import io.reactivex.Single
import javax.inject.Inject

class SuggestsRepoImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val suggestionsMapper: SuggestionsMapper
) : SuggestsRepo {
    override fun getSuggests(query: String): Single<Resource<List<String>>> {
        //firefox = json output as API documentation
        return apiServices.requestSuggestions(query, "firefox").map(ResourceMapper(suggestionsMapper)::map)
    }
}