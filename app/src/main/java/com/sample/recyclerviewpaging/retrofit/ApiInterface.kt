package com.sample.recyclerviewpaging.retrofit

import androidx.lifecycle.LiveData
import com.sample.recyclerviewpaging.BuildConfig
import com.sample.recyclerviewpaging.model.UserDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api/users?")
    fun getUserList(@Query("page") page:Int): Call<UserDetails>

//    @GET("/api/users?")
//    fun getUserList1(@Query("page") page:Int): LiveData<UserDetails>
}