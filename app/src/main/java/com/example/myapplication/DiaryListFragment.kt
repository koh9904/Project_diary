package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentDiaryListBinding

class DiaryListFragment : Fragment() {
    private var _binding: FragmentDiaryListBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Binding Error")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDiaryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.diarlylist.adapter = DiaryAdapter { diary ->
            DiaryListFragmentDirections.actionDiaryListFragmentToDiaryFragment(
                diary
            ).let { action ->
                findNavController().navigate(action)
            }
        }

        (0..15).reversed().map {
            DiaryItem(it+1, "오늘의 일기 ${it+1}편", "11월${it+6}일", if(it%2 == 0)"male" else "female")
        }.let {
            (binding.diarlylist.adapter as DiaryAdapter).changeDiaryData(it)
            (binding.diarlylist.adapter as DiaryAdapter).notifyDataSetChanged()
        }
    }

    companion object {
        const val SELECTED_DIARY_DATA = "SELECTED_DIARY_DATA"
        const val SELECTED_DIARY = "SELECTED_DIARY"
    }
}