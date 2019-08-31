package com.task.searchbar.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.task.searchbar.domain.usecase.GetUriUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getUriUseCase: GetUriUseCase) : ViewModel() {
    fun getUri(query: String): Uri {
        return getUriUseCase.getUri(query)
    }
}
