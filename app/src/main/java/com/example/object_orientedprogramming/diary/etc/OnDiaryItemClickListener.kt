package com.example.object_orientedprogramming.diary.etc

import android.view.View
import com.example.object_orientedprogramming.diary.DiaryAdapter

interface OnDiaryItemClickListener {
    fun onItemClick(holder: DiaryAdapter.ViewHolder, view: View, position: Int)
}