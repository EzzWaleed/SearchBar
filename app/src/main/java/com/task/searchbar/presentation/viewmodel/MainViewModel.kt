package com.task.searchbar.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.searchbar.data.resource.Resource
import com.task.searchbar.domain.usecase.GetUriUseCase
import com.task.searchbar.domain.usecase.SuggestUseCase
import com.task.searchbar.presentation.viewmodel.viewmodel_factory.SchedulersModule.Companion.IO_SCHEDULER
import com.task.searchbar.presentation.viewmodel.viewmodel_factory.SchedulersModule.Companion.MAIN_THREAD_SCHEDULER
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
    @Named(value = IO_SCHEDULER) private val ioScheduler: Scheduler,
    @Named(value = MAIN_THREAD_SCHEDULER) private val mainScheduler: Scheduler,
    private val getUriUseCase: GetUriUseCase,
    private val suggestUseCase: SuggestUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val queryPublishSubject = PublishSubject.create<String>()

    private val suggestionsLiveData = MutableLiveData<Resource<List<String>>>()

    fun getUri(query: String): Uri {
        return getUriUseCase.getUri(query)
    }

    fun initQueryObservation() {

        queryPublishSubject
            .observeOn(mainScheduler)
            .debounce(400, TimeUnit.MILLISECONDS)
            .switchMap {
                suggestUseCase.getSuggestions(it)
                    .subscribeOn(ioScheduler)
                    .doOnSubscribe { suggestionsLiveData.postValue(Resource.loading()) }
            }
            .doOnError{suggestionsLiveData.value = Resource.domainError(it)}




    }

    fun getSuggestions(query: String) {

    }

    fun getSuggestionsLiveData(): LiveData<Resource<List<String>>> {
        return suggestionsLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
