package com.task.searchbar.data.mapper.base

import androidx.lifecycle.Transformations.map

abstract class EntityMapper<IN, OUT> {

    abstract fun map(input: IN): OUT

    fun mapList(inList: List<IN>): List<OUT> {
        val outList = ArrayList<OUT>()
        for (`in` in inList) {
            outList.add(map(`in`))
        }
        return outList
    }
}