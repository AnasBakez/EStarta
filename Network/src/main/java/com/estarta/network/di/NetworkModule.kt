package com.estarta.network.di

import com.estarta.network.BuildConfig
import com.estarta.network.api.ApiConstants
import com.estarta.network.api.ApiService
import com.estarta.network.calladapter.RxJavaCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    companion object {
        @Provides
        @Singleton
        internal fun provideGson(): Gson = GsonBuilder().create()

        @Provides
        @Singleton
        internal fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .also {
                    if (BuildConfig.DEBUG) {
                        it.addInterceptor(createLoggingInterceptor())
                    }
                }
                .build()
        }

        private fun createLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }
        }

        @Provides
        @Singleton
        internal fun provideRetrofit(
            gson: Gson,
            okhttpClient: OkHttpClient,
            rxFactory: CallAdapter.Factory,
        ): Retrofit {
            val builder = Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okhttpClient)
                .apply {
                    addCallAdapterFactory(rxFactory)
                }
            return builder.build()
        }

        @Provides
        @Singleton
        internal fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }

    }

    @Binds
    @Singleton
    abstract fun provideRxCallAdapterFactory(impl: RxJavaCallAdapterFactory): CallAdapter.Factory

}