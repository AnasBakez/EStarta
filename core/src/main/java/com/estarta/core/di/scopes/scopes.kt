package com.estarta.core.di.scopes

import javax.inject.Scope

// Creates MyCustomScope
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ModuleScope
