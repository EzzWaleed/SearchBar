package com.task.searchbar.data.mapper.base

import com.task.searchbar.data.resource.Resource
import retrofit2.Response
import javax.inject.Inject

class ResourceMapper<IN, OUT> @Inject constructor(private val entityMapper: EntityMapper<IN, OUT>) {
    fun map(response: Response<IN>): Resource<OUT> {
        return if (response.code() == 200)
            Resource.success(entityMapper.map(response.body()!!))
        else Resource.apiError(
            response.message(),
            response.code()
        )
    }

    fun mapList(response: Response<List<IN>>): Resource<List<OUT>> {
        return if (response.code() == 200)
            Resource.success(entityMapper.mapList(response.body()!!))
        else Resource.apiError(
            response.message(),
            response.code()
        )
    }

}
