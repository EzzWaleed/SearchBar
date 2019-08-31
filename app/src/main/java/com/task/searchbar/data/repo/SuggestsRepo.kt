package com.task.searchbar.data.repo

import com.task.searchbar.data.resource.Resource
import io.reactivex.Single

interface SuggestsRepo {
    fun getSuggests(query: String): Single<Resource<List<String>>>
}