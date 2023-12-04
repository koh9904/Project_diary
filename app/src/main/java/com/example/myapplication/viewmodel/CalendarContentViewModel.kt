package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.DiaryItem
import com.example.myapplication.DiaryListFragment.Companion.dateFormat
import com.example.myapplication.repository.CalendarContentRepository
import java.util.Date
import kotlin.random.Random

class CalendarContentViewModel: ViewModel() {
    private val _content = MutableLiveData("")
    val content : LiveData<String> get() = _content

    private val repository = CalendarContentRepository()

    private val _selectedDate = MutableLiveData("")

    fun setSelectedDate(date: String){
        _selectedDate.value = date
        observeContentForSelectedDate()
    }

    fun saveContent(newValue: String){
        _selectedDate.value?.let{ date->
            if(newValue.isNotBlank()){
                repository.postContent(
                    DiaryItem(
                        id = "",
                        userId = "userId${Random.nextInt(500)}",
                        content = newValue,
                        date = date,
                        image = "https://picsum.photos/id/${Random.nextInt(400)}/300/200"
                    )
                )
            } else {
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