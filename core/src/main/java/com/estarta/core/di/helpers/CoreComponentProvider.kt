package com.estarta.core.di.helpers

import com.estarta.core.di.components.CoreComponent

interface CoreComponentProvider {
    fun provideCoreComponent() : CoreComponent
}