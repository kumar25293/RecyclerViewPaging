package com.sample.recyclerviewpaging.retrofit

import android.content.Context
import com.google.gson.GsonBuilder
import com.sample.recyclerviewpaging.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    var baseurl = BuildConfig.API_URL

    private const val REQUEST_TIMEOUT:Long = 60


    fun getRetrofitClient(context: Context): Retrofit? {
        val okHttpClient =initOkHttp(context)
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private fun initOkHttp(context: Context): OkHttpClient {
        val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .cache(null)
        return httpClient.build()
    }

}