package com.gmail.victorchuholskiy.marketplace.di

import javax.inject.Scope
import kotlin.annotation.Retention
import kotlin.annotation.MustBeDocumented

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 * In Dagger, an unscoped component cannot depend on a scoped component. As
 * {@link AppComponent} is a scoped component ({@code @Singleton}, we create a custom
 * scope to be used by all fragment components. Additionally, a component with a specific scope
 * cannot have a sub component with the same scope.
 */
@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped
