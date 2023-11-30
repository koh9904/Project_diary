package com.example.myapplication.dataSource

import com.example.myapplication.DiaryItem

interface DiaryDataSource {
    fun getAll(
        successListener: (List<DiaryItem>) -> Unit
    )
}