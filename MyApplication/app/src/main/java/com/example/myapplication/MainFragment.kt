package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var binding: FragmentMainBinding

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater).apply {
            calendarView.setOnDateChangeListener{ calendarView, year, month, dayOfMonth ->
                val day: String = "${year}년 ${month+1}월 ${dayOfMonth}일"
                dayText.text = day
            }

        }
        return binding.root
    }

    companion object {

    }
}