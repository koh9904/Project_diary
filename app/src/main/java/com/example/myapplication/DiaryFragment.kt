package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentDiaryBinding
import com.example.myapplication.viewmodel.DiaryViewModel


class DiaryFragment : Fragment() {
    private var _binding: FragmentDiaryBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Binding Error")
    private val viewModel by viewModels<DiaryViewModel>()
    private val args: DiaryFragmentArgs by navArgs()

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

        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        loadDiary()
        updateDiary()
        deleteDiary()
    }

    private fun loadDiary() {
        args.diary?.let { diary ->
            viewModel.changeDiary(diary)
            binding.run {
                Glide.with(tDimage.context).load(diary.image).into(tDimage)
                binding.tDdate.text = diary.date
                binding.tDcontent.setText(diary.content)
            }
        }
    }

    private fun updateDiary() {
        binding.save.setOnClickListener {
            viewModel.updateDiary(
                binding.tDcontent.text.toString()
            )
            Toast.makeText(requireContext(), "일기를 수정했어요!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteDiary() {
        binding.delete.setOnClickListener {
            Toast.makeText(requireContext(), "일기를 삭제했어요!", Toast.LENGTH_SHORT).show()
            viewModel.deleteDiary()
            findNavController().popBackStack()
        }
    }
}