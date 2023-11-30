package com.example.myapplication

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataSource.DiaryDataSource
import com.example.myapplication.dataSource.DiaryDataSourceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DiaryListViewModel: ViewModel() {
    private val diaryDataSource: DiaryDataSource = DiaryDataSourceImpl()

    private val _diaries = MutableStateFlow<List<DiaryItem>>(emptyList())
    val diaries = _diaries.asStateFlow()

    fun getAllDiaries() {
        diaryDataSource.getAll {
            _diaries.value = it
        }
    }
}