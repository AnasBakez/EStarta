package com.estarta.core.di.helpers

import android.content.Context
import com.estarta.core.di.components.CoreComponent

object CoreInjectHelper {
    fun provideCoreComponent(applicationContext: Context): CoreComponent {
        return if (applicationContext is CoreComponentProvider) {
            (applicationContext as CoreComponentProvider).provideCoreComponent()
        } else {
            throw IllegalStateException("The application context you have passed does not implement CoreComponentProvider")
        }
    }

    fun provideModulesCommunicator(applicationContext: Context): ModulesCommunicator {
        return if (applicationContext is ModulesCommunicationProvider) {
            (applicationContext as ModulesCommunicationProvider).provideModulesCommunicator()
        } else {
            throw IllegalStateException("The application context you have passed does not implement ModulesCommunicationProvider")
        }
    }
}