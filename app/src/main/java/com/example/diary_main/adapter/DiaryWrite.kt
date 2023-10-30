package com.example.diary_main.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.diary_main.R

class DiaryWrite : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.diary_write, container, false)

        val save = view.findViewById<Button>(R.id.save)
        save.setOnClickListener {
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
        }

        val cancel = view.findViewById<Button>(R.id.cancel)
        cancel.setOnClickListener {
            Toast.makeText(requireContext(), "Canceled", Toast.LENGTH_LONG).show()
        }
        return view
    }
}