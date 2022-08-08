package com.estarta.core.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

//    @Singleton
//    @Provides
//    fun provideSharedPref(context: Context) : SharedPrefsUtil {
//        return SharedPrefsUtil(context)
//    }
}