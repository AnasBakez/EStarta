package com.estarta.estarta_task

import android.app.Application
import com.estarta.core.di.components.CoreComponent
import com.estarta.core.di.components.DaggerCoreComponent
import com.estarta.core.di.helpers.CoreComponentProvider
import com.estarta.core.di.helpers.ModulesCommunicationProvider
import com.estarta.core.di.helpers.ModulesCommunicator

class MyApplication() : Application(), CoreComponentProvider, ModulesCommunicationProvider {

    override fun provideCoreComponent(): CoreComponent {
        return DaggerCoreComponent.builder().application(this).build();
    }

    override fun provideModulesCommunicator(): ModulesCommunicator {
        TODO("Not yet implemented")
    }
}