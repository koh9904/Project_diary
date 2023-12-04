package com.example.myapplication.dataSource

import com.example.myapplication.DiaryItem

interface DiaryDataSource {
    fun update(diaryItem: DiaryItem)
    fun delete(id: String)
    fun getAll(successListener: (List<DiaryItem>) -> Unit)
}