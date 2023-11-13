package com.example.object_orientedprogramming.diary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.object_orientedprogramming.R
import com.example.object_orientedprogramming.diary.etc.DiaryItem
import com.example.object_orientedprogramming.diary.etc.OnDiaryItemClickListener
import com.example.object_orientedprogramming.diary.etc.OnTabItemSelectedListener

class DiaryListFragment : Fragment() {
    private lateinit var adapter: DiaryAdapter
    private lateinit var recyclerView: RecyclerView
    var listener: OnTabItemSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val rootView: View = inflater.inflate(R.layout.fragment_diary_list, container, false)

        initUI(rootView)

        return rootView
    }

     private fun initUI(rootView: View) {
        recyclerView = rootView.findViewById(R.id.diarlylist)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        val items = ArrayList<DiaryItem>()

        items.add(DiaryItem(0, "오늘의 일기 6편", "11월11일", "female"))
        items.add(DiaryItem(1, "오늘의 일기 5편", "11월10일", "male"))
        items.add(DiaryItem(2, "오늘의 일기 4편", "10월11일", "female"))
        items.add(DiaryItem(3, "오늘의 일기 3편", "10월10일", "male"))
        items.add(DiaryItem(4, "오늘의 일기 2편", "1월11일", "female"))
        items.add(DiaryItem(5, "오늘의 일기 1편", "1월10일", "male"))

        adapter = DiaryAdapter(items)

        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: OnDiaryItemClickListener {
            override fun onItemClick(holder: DiaryAdapter.ViewHolder, view: View, position: Int) {
                val item: DiaryItem = adapter.getItem(position)
                Log.d("DiaryListFragment", "Selected :" + item.id)
                listener?.showDiaryFragment(item)
            }
        })
    }
}