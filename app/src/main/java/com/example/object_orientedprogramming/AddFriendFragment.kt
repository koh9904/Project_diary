package com.example.object_orientedprogramming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.object_orientedprogramming.databinding.FragmentAddFriendBinding

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