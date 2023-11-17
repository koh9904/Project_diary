package com.example.object_orientedprogramming.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.object_orientedprogramming.databinding.FragmentDiaryListBinding
import com.example.object_orientedprogramming.diary.etc.DiaryItem

class DiaryListFragment : Fragment() {
    private var _binding: FragmentDiaryListBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Binding Error")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDiaryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initUI()
    }

    private fun initUI() {
        binding.diarlylist.adapter = DiaryAdapter { diary ->
            Bundle().also {
                it.putParcelable(SELECTED_DIARY_DATA, diary)
            }.also {
                requireActivity().supportFragmentManager.setFragmentResult(SELECTED_DIARY, it)
            }
        }

        /**
         * DB 붙이기 전에 우선 16개의 데이터를 임시로 생성 했습니다.
         * List<DiaryItem>를 changeDiaryData로 모두 한번에 로드 해주고
         * RecyclerView에 데이터가 바뀌었으니 UI 새로 그려라 라는 함수(notifyDataSetChanged)를 호출 합니다.
         * **/
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