package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.DiaryItem
import com.example.myapplication.dataSource.DiaryDataSource
import com.example.myapplication.dataSource.DiaryDataSourceImpl

class DiaryViewModel: ViewModel() {
    private val diaryDataSource: DiaryDataSource = DiaryDataSourceImpl()

    private val _diary = MutableLiveData<DiaryItem>()
    val diary = _diary

    fun changeDiary(diary: DiaryItem) {
        _diary.value = diary
    }

    fun updateDiary(content: String) {
        diary.value?.copy(content = content)
            ?.let { updatedDiary ->
                diaryDataSource.update(updatedDiary)
            }
    }

    fun deleteDiary() {
        diary.value?.id?.let { id ->
            diaryDataSource.delete(id)
        }
    }
}