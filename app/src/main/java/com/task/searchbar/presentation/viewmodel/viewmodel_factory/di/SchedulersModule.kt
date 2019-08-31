package com.task.searchbar.presentation.viewmodel.viewmodel_factory.di

import javax.inject.Named

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ezz Waleed on 08,March,2019
 */
@Module
class SchedulersModule {

    @Provides
    @Named(value = IO_SCHEDULER)
    fun bindIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Named(value = MAIN_THREAD_SCHEDULER)
    fun bindMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {
        const val IO_SCHEDULER = "IO_SCHEDULER"
        const val MAIN_THREAD_SCHEDULER = "MAIN_THREAD_SCHEDULER"
    }
}
