package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFriendMainBinding
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.viewmodel.CalendarContentViewModel
import com.example.myapplication.viewmodel.SettingViewModel
import com.example.myapplication.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class FriendMainFragment : Fragment() {
    private val calendarViewModel: CalendarContentViewModel by activityViewModels()
    private var binding: FragmentFriendMainBinding ?= null
    private val model: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendMainBinding.inflate(inflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
        val todayDate = dateFormat.format(currentDate)

        model.userName.observe(viewLifecycleOwner) { userName ->
            binding?.txtTitle?.text = "$userName's Diary"
        }

        binding?.btnBack?.setOnClickListener {
            findNavController().navigate(R.id.action_friendMainFragment_to_friendsFragment)
        }

        binding?.dayText?.text = todayDate
        calendarViewModel.setSelectedDate(todayDate)

        binding?.calendarView?.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            val date = "${year}년 ${month + 1}월 ${dayOfMonth}일"
            binding?.dayText?.text = date
            calendarViewModel.setSelectedDate(date)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}