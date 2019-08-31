package com.task.searchbar.app

import android.app.Application
import com.task.searchbar.app.di.DaggerAppComponent
import com.task.searchbar.domain.di.DaggerDomainComponent
import com.task.searchbar.presentation.di.DaggerPresentationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .presentationComponent(
                DaggerPresentationComponent.builder().domainComponent(
                    DaggerDomainComponent.builder().bindsContext(this).build()
                ).build()
            ).build()
            .inject(this)
    }
}
