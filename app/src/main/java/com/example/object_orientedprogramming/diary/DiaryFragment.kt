package com.example.object_orientedprogramming.diary

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.object_orientedprogramming.R
import com.example.object_orientedprogramming.databinding.FragmentDiaryBinding
import com.example.object_orientedprogramming.diary.DiaryListFragment.Companion.SELECTED_DIARY_DATA
import com.example.object_orientedprogramming.diary.etc.AppConstants
import com.example.object_orientedprogramming.diary.etc.DiaryItem


class DiaryFragment : Fragment() {
    private var _binding: FragmentDiaryBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Binding Error")
    private var mMode = AppConstants.MODE_INSERT
    private var resultPhotoBitmap : Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        mMode = AppConstants.MODE_MODIFY

        /**
         * 뒤로가기 버튼 클릭시 이전 다이어리 리스트가 나와야합니다.
         * 이전 DiaryFragment 뒤에 DiaryListFragment가 스택에 쌓여있기 때문에 popBackStack()을 이용하여 꺼내 오면됩니다.
         * **/
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        loadDiary()
    }
    private fun loadDiary() {
        val diary = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(SELECTED_DIARY_DATA, DiaryItem::class.java)
        } else {
            arguments?.getParcelable(SELECTED_DIARY_DATA) as DiaryItem?
        }

        if(diary != null) {
            if(diary.image == "female") {
                binding.tDimage.setImageResource(R.drawable.female)
            } else {
                binding.tDimage.setImageResource(R.drawable.male)
            }
        }
    }


}