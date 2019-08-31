package com.task.searchbar.domain.query.processor.mapper.di

import com.task.searchbar.domain.query.processor.QueryType
import com.task.searchbar.domain.query.processor.mapper.QueryMapper
import com.task.searchbar.domain.query.processor.mapper.factory.di.QueryKey
import com.task.searchbar.domain.query.processor.mapper.impl.FacebookMapper
import com.task.searchbar.domain.query.processor.mapper.impl.LinkMapper
import com.task.searchbar.domain.query.processor.mapper.impl.MailMapper
import com.task.searchbar.domain.query.processor.mapper.impl.SearchMapper
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MapperModule {
    @Binds
    @IntoMap
    @QueryKey(QueryType.FACEBOOK)
    fun bindsFacebookMapper(facebookMapper: FacebookMapper): QueryMapper

    @Binds
    @IntoMap
    @QueryKey(QueryType.LINK)
    fun bindsLinkMapper(linkMapper: LinkMapper): QueryMapper

    @Binds
    @IntoMap
    @QueryKey(QueryType.MAIL)
    fun bindsMailMapper(mailMapper: MailMapper): QueryMapper

    @Binds
    @IntoMap
    @QueryKey(QueryType.SEARCH)
    fun bindsSearchMapper(searchMapper: SearchMapper): QueryMapper
}