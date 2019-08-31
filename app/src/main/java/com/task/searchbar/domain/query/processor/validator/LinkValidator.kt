package com.task.searchbar.domain.query.processor.validator

interface LinkValidator {
    fun isLink(query: String): Boolean
    fun isFacebookLink(query: String): Boolean
    fun isMailLink(query: String): Boolean
}
