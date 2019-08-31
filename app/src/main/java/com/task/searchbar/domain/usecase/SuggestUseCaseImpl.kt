package com.task.searchbar.domain.usecase

import com.task.searchbar.data.repo.SuggestsRepo
import com.task.searchbar.data.resource.Resource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SuggestUseCaseImpl @Inject constructor(private val suggestsRepo: SuggestsRepo) :
    SuggestUseCase {
    override fun getSuggestions(query: String): Observable<Resource<List<String>>> {
        return suggestsRepo.getSuggests(query).toObservable()
    }
}