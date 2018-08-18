package com.gmail.victorchuholskiy.marketplace.di

import kotlin.annotation.Target
import kotlin.annotation.Retention
import javax.inject.Scope

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class FragmentScoped