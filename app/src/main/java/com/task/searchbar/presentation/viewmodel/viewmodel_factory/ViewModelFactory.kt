package com.task.searchbar.presentation.viewmodel.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Ezz Waleed on 07,March,2019
 */

/**
 * unique ViewModel Factory that provides
 * all kind of ViewModel we need based from the modules inside the component
 * using dagger multibindings.
 */
class ViewModelFactory @Inject
constructor(private val viewModelMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = viewModelMap[modelClass]
        val entries = viewModelMap.entries
        for ((key) in entries) {
            if (key.isAssignableFrom(modelClass)) {
                return provider!!.get() as T
            }
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}
