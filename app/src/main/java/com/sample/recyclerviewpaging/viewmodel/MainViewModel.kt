package com.sample.recyclerviewpaging.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.recyclerviewpaging.model.UserDetails
import com.sample.recyclerviewpaging.repository.Respository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var userlist: MutableLiveData<List<UserDetails.Data>> = MutableLiveData<List<UserDetails.Data>>()
    lateinit  var repository:Respository

    fun getUserDetails(con: Context, pagenum:Int): MutableLiveData<List<UserDetails.Data>>{

        repository= Respository()
        userlist = repository.getuserList(con,pagenum)

        return userlist
    }

    fun getTotelPage():Int{
       return repository.getTotalPage()
    }


}