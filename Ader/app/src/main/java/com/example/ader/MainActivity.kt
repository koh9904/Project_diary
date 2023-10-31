package com.example.ader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater) //외워야?
        setContentView(binding.root) //두 줄은 뷰 바인딩을 위해 반드시 필요한 줄

        binding.sum.setOnClickListener {
            val str = binding.editNum1.text.toString()
            val num = str.toInt()
            val str2 = binding.editNum2.text.toString()
            val num2 = str2.toInt()

            binding.txtResult.text = (num + num2).toString()
        }
    }
}