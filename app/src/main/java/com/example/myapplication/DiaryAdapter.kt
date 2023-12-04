package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemDiarylistBinding

class DiaryAdapter(private val onClickItem: (DiaryItem) -> Unit) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {
    private var diaries = mutableListOf<DiaryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val binding = ItemDiarylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiaryViewHolder(binding)
    }

    override fun getItemCount(): Int = diaries.count()

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val diaryItem = diaries[position]
        holder.binding.apply {
            content.text = diaryItem.content
            card.setOnClickListener { onClickItem(diaryItem) }
            date.text = diaryItem.date
            Glide.with(image.context)
                .load(diaryItem.image)
                .into(image)
        }
    }

    fun changeDiaryData(diaries: List<DiaryItem>) {
        this.diaries = diaries.toMutableList()
    }

    class DiaryViewHolder(val binding: ItemDiarylistBinding): RecyclerView.ViewHolder(binding.root)
}