package com.task.searchbar.domain.usecase

import android.net.Uri

interface GetUriUseCase {
    fun getUri(query: String): Uri
}