package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.UserData
import com.example.myapplication.repository.UserRepo

class UserViewModel: ViewModel() {
    private val repo = UserRepo()

    val userName: MutableLiveData<String> = MutableLiveData()
    fun fetchData(): LiveData<MutableList<UserData>> {
        val mutableData = MutableLiveData<MutableList<UserData>>()
        repo.observeUser().observeForever{
            mutableData.value = it
        }
        return mutableData
    }

}