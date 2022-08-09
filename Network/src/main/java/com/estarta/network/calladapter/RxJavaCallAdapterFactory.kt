package com.estarta.network.calladapter

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type
import javax.inject.Inject

class RxJavaCallAdapterFactory @Inject constructor() : CallAdapter.Factory() {
    private val factory = RxJava2CallAdapterFactory.create()
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return factory.get(returnType, annotations, retrofit)
    }
}