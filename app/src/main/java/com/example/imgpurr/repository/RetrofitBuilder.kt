package com.example.imgpurr.repository

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {

    private val httpClient: OkHttpClient
        get() {
            val headerInterceptor = Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", HEADER_AUTHORIZATION_VALUE)
                    .build()
                chain.proceed(request)
            }

            return OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .build()
        }

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()

    fun <T> createService(
        serviceClass: Class<T>
    ): T {
        return retrofit
            .create(serviceClass)
    }


    companion object {
        private const val BASE_URL = "https://api.imgur.com/3/"
        private const val HEADER_AUTHORIZATION_VALUE = "Client-ID 1ceddedc03a5d71"
    }

}