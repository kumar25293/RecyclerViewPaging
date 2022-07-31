package com.sample.recyclerviewpaging.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.recyclerviewpaging.model.UserDetails
import com.sample.recyclerviewpaging.retrofit.ApiInterface
import com.sample.recyclerviewpaging.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Respository() {

    private val userlist: MutableLiveData<List<UserDetails.Data>> = MutableLiveData<List<UserDetails.Data>>()
    var totalpage:Int= 0


    fun getuserList(context:Context,pagenum:Int): MutableLiveData<List<UserDetails.Data>> {

        val retrofit = RetrofitClient.getRetrofitClient(context)?.create(ApiInterface::class.java)
        val apireq = retrofit?.getUserList(pagenum)

        apireq?.enqueue(object :Callback<UserDetails>{
            override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                val statuscode =response.code()
                if(statuscode==200){
                    totalpage = response.body()?.totalPages!!
                    totalpage =5
                    if(!response.body()?.data.isNullOrEmpty()!!){
                        userlist.value = response.body()!!.data;
                    }
                }
            }

            override fun onFailure(call: Call<UserDetails>, t: Throwable) {

            }
        })
        return  userlist;

    }

    fun getTotalPage():Int
    {
        return  totalpage
    }
}