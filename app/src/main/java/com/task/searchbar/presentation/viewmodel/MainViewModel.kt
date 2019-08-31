package com.task.searchbar.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.searchbar.data.resource.Resource
import com.task.searchbar.domain.usecase.GetUriUseCase
import com.task.searchbar.domain.usecase.SuggestUseCase
import com.task.searchbar.presentation.viewmodel.viewmodel_factory.di.SchedulersModule.Companion.IO_SCHEDULER
import com.task.searchbar.presentation.viewmodel.viewmodel_factory.di.SchedulersModule.Companion.MAIN_THREAD_SCHEDULER
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
    @param:Named(IO_SCHEDULER) private val ioScheduler: Scheduler,
    @param:Named(MAIN_THREAD_SCHEDULER) private val mainScheduler: Scheduler,
    private val getUriUseCase: GetUriUseCase,
    private val suggestUseCase: SuggestUseCase
) : ViewModel() {

    var queryState: String? = null

    private val queryPublishSubject = PublishSubject.create<String>()

    private val compositeDisposable = CompositeDisposable()

    private val suggestionsLiveData = MutableLiveData<Resource<List<String>>>()


    init {
        initQueryObservation()
    }


    fun getUri(query: String): Uri {
        queryState = query
        return getUriUseCase.getUri(query)
    }

    private fun initQueryObservation() {
        val disposable = queryPublishSubject
            .observeOn(mainScheduler)
            .debounce(400, TimeUnit.MILLISECONDS)
            .switchMap {
                suggestUseCase.getSuggestions(it)
                    .subscribeOn(ioScheduler)
                    .doOnSubscribe { suggestionsLiveData.postValue(Resource.loading()) }
            }
            .doOnError { suggestionsLiveData.postValue(Resource.domainError(it)) }
            .retryWhen { it.map { Observable.just(Any()) } }
            .subscribe { suggestionsLiveData.postValue(it) }

        compositeDisposable.add(disposable)
    }

    fun getSuggestions(query: String) {
        queryPublishSubject.onNext(query)
    }

    fun getSuggestionsLiveData(): LiveData<Resource<List<String>>> {
        return suggestionsLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
