package com.task.searchbar.domain.query.processor.validator.di

import com.task.searchbar.domain.di.DomainScope
import com.task.searchbar.domain.query.processor.validator.LinkValidator
import com.task.searchbar.domain.query.processor.validator.LinkValidatorImpl
import dagger.Binds
import dagger.Module

@Module
interface ValidatorModule {
    @Binds
    @DomainScope
    fun bindsLinkValidator(linkValidatorImpl: LinkValidatorImpl): LinkValidator
}