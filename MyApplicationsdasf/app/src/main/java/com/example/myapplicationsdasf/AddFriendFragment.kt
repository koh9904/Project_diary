package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentAddFriendBinding

class AddFriendFragment : Fragment() {

    lateinit var binding : FragmentAddFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddFriendBinding.inflate(layoutInflater)
        return binding.root
    }

}