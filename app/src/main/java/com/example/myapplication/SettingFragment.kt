package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentSettingBinding
import com.example.myapplication.viewmodel.SettingViewModel

class SettingFragment : Fragment() {
    val viewModel: SettingViewModel by activityViewModels()
    var binding : FragmentSettingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(layoutInflater)

        return binding?.root
    }
//d
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.name.observe(viewLifecycleOwner) {
            binding?.txtSettingName?.text = viewModel.name.value
        }
        viewModel.name.observe(viewLifecycleOwner) {
            binding?.txtSettingIm?.text = viewModel.im.value
        }

        binding?.btnSet?.setOnClickListener {
            binding?.txtSettingName?.text = binding?.txtInputName?.editableText
            binding?.txtSettingIm?.text = binding?.txtInputIm?.editableText
        }
    }
}