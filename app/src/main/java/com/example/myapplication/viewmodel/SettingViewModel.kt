package com.example.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.SettingRepository

class SettingViewModel: ViewModel() {
    private val _name = MutableLiveData<String>("유승빈")
    private val _im = MutableLiveData<String>("방가")
    val name : LiveData<String> get() = _name
    val im : LiveData<String> get() = _im

    private val repository = SettingRepository()


    fun saveName(newName: String){
        _name.value = newName
        repository.postName(newName)


    }


}