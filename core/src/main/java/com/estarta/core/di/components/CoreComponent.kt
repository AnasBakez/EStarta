package com.estarta.core.di.components

import android.app.Application
import android.content.Context
import com.estarta.core.di.modules.CoreModule
import com.estarta.network.api.ApiService
import com.estarta.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [CoreModule::class, NetworkModule::class])
interface CoreComponent {


    fun context() : Context
    fun application() : Application

    fun apiService() : ApiService






    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : CoreComponent
    }
}