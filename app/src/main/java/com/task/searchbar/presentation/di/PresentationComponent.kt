package com.task.searchbar.presentation.di
import com.task.searchbar.domain.di.DomainComponent
import com.task.searchbar.presentation.viewmodel.viewmodel_factory.ViewModelFactory
import com.task.searchbar.presentation.viewmodel.viewmodel_factory.di.ViewModelFactoryModule
import dagger.Component

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Component(
    modules = [ViewModelFactoryModule::class],
    dependencies = [DomainComponent::class]
)
@PresentationScope
interface PresentationComponent {
    fun viewModelFactory(): ViewModelFactory
}
