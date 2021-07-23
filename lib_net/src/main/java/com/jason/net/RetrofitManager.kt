package com.jason.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private const val TIME_OUT = 60L

    val retrofit : Retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { createRetrofit() }

    private fun createRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient():OkHttpClient{
        val builder:OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT,TimeUnit.SECONDS)
            .readTimeout(TIME_OUT,TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        builder.addInterceptor(httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        })
        return builder.build()
    }
}