package com.estarta.core.di.components

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component/*( modules = [CoreModule::class, DatabaseModule::class])*/
interface CoreComponent {


    fun context() : Context
    fun application() : Application

//    fun database() : AppDataBase






    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : CoreComponent
    }
}