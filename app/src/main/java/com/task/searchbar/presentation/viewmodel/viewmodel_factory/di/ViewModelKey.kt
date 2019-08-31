package com.task.searchbar.presentation.viewmodel.viewmodel_factory.di

import androidx.lifecycle.ViewModel

import dagger.MapKey

import java.lang.annotation.ElementType.METHOD
import kotlin.reflect.KClass

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
