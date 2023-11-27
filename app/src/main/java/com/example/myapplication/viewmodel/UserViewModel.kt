package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    private val _user = MutableLiveData<String>("안녕하세요")
    val user : LiveData<String> get() = _user


}