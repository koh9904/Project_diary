package com.example.object_orientedprogramming.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.object_orientedprogramming.R

class DiaryListFragment : Fragment() {
    private lateinit var adapter: DiaryAdapter
    private lateinit var recyclerView: RecyclerView

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

        adapter = DiaryAdapter()

        recyclerView.adapter = adapter

        adapter.addItem(
            DiaryItem(0, "집에 가고싶다...수업 듣기 싫다", "11월11일", "female")
        )
        adapter.addItem(
            DiaryItem(1, "집에 가고싶다...수업 듣기 싫다", "11월10일", "female")
        )
        adapter.addItem(
            DiaryItem(2, "집에 가고싶다...수업 듣기 싫다", "10월11일", "female")
        )
        adapter.addItem(
            DiaryItem(3, "집에 가고싶다...수업 듣기 싫다", "10월10일", "female")
        )
        adapter.addItem(
            DiaryItem(4, "집에 가고싶다...수업 듣기 싫다", "1월11일", "female")
        )
        adapter.addItem(
            DiaryItem(5, "집에 가고싶다...수업 듣기 싫다", "1월10일", "female")
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DiaryListFragment()
    }
}