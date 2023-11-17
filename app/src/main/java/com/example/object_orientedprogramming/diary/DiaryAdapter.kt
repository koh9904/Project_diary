package com.example.object_orientedprogramming.diary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.object_orientedprogramming.databinding.ListItemBinding
import com.example.object_orientedprogramming.diary.etc.DiaryItem

/**
 * RecyclerView에 설정해 줄 RecyclerView.Adapter 입니다.
 **/

class DiaryAdapter(private val onClickItem: (DiaryItem) -> Unit) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {
    private var diaries = mutableListOf<DiaryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiaryViewHolder(binding)
    }

    override fun getItemCount(): Int = diaries.count()

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val diaryItem = diaries[position]
        holder.binding.diaryItem = diaryItem
        holder.binding.card.setOnClickListener { onClickItem(diaryItem) }
    }

    fun changeDiaryData(diaries: List<DiaryItem>) {
        this.diaries = diaries.toMutableList()
    }

    class DiaryViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)
}