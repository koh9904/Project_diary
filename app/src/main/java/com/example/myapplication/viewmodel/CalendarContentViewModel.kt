package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.CalendarContentRepository

class CalendarContentViewModel: ViewModel() {
    private val _content = MutableLiveData<String>("")
    val content : LiveData<String> get() = _content

    private val repository = CalendarContentRepository()

    private val _selectedDate = MutableLiveData<String>("")

    fun setSelectedDate(date: String){
        _selectedDate.value = date
        observeContentForSelectedDate()
    }

    fun saveContent(newValue: String){
        _selectedDate.value?.let{date->
            if(newValue.isNotBlank()){
                repository.postContent(date, newValue)
            }else{
                repository.deleteContent(date)
            }
        }
    }

    private fun observeContentForSelectedDate(){
        _selectedDate.value?.let{date->
            repository.observeContent(date, _content)
        }
    }

}