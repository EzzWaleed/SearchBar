package com.task.searchbar.data.remote

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("search")
    fun requestSuggestions(@Query(value = "q") query: String, @Query(value = "client") output: String): Single<Response<List<Any>>>
}

