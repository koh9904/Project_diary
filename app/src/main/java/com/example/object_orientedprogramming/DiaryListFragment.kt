package com.example.object_orientedprogramming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


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
        val layoutManager = LinearLayoutManager (context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        adapter = DiaryAdapter()

        recyclerView.adapter = adapter

        adapter.addItem(
            diaryitem(0,"집에 가고싶다...수업 듣기 싫다", "11월11일", "female")
        )
        adapter.addItem(
            diaryitem(1,"집에 가고싶다...수업 듣기 싫다", "11월10일", "female")
        )
        adapter.addItem(
            diaryitem(2,"집에 가고싶다...수업 듣기 싫다", "10월11일", "female")
        )
        adapter.addItem(
            diaryitem(3,"집에 가고싶다...수업 듣기 싫다", "10월10일", "female")
        )
        adapter.addItem(
            diaryitem(4,"집에 가고싶다...수업 듣기 싫다", "1월11일", "female")
        )
        adapter.addItem(
            diaryitem(5,"집에 가고싶다...수업 듣기 싫다", "1월10일", "female")
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiaryListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}