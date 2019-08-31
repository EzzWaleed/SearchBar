package com.task.searchbar.domain.usecase

import com.task.searchbar.data.resource.Resource
import io.reactivex.Observable

interface SuggestUseCase {
    fun getSuggestions(query: String): Observable<Resource<List<String>>>
}