package com.atr.schoolconnect.data.controller

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestAdapter {

    private var API_BASE_URL = ApiUrls.BASE_URL
    val client = getOkHttpClient( enableLogging = true)


    val restService: RestService by lazy {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RestService::class.java)
    }
    private fun getOkHttpClient(enableLogging: Boolean) : OkHttpClient{

        val okHttpClientBuilder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)

        if (enableLogging) {
            val loggingInterceptor = HttpLoggingInterceptor { message ->
                Log.d("response", message)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        val timeout = 100L // Timeout duration in seconds
        okHttpClientBuilder
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }


}