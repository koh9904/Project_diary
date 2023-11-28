package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.viewmodel.CalendarContentViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainFragment : Fragment() {
    private val viewModel: CalendarContentViewModel by activityViewModels()
    private var binding: FragmentMainBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
        val todayDate = dateFormat.format(currentDate)

        binding?.dayText?.text = todayDate
        viewModel.setSelectedDate(todayDate)

        viewModel.content.observe(viewLifecycleOwner){content->
            binding?.editTextText?.text = Editable.Factory.getInstance().newEditable(content)
        }

        binding?.calendarView?.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            val date: String = "${year}년 ${month + 1}월 ${dayOfMonth}일"
            binding?.dayText?.text = date
            viewModel.setSelectedDate(date)

            binding?.editTextText?.text?.clear()
        }

        binding?.btnSave?.setOnClickListener {
            val diaryContent = binding?.editTextText?.text.toString()
            viewModel.saveContent(diaryContent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}