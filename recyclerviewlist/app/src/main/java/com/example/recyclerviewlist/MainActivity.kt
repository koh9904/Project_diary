package com.example.recyclerviewlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val friends = arrayOf(
        Friends("유승빈", "안녕하세요", EGender.MALE),
        Friends("김세현", "반가워요", EGender.FEMALE),
        Friends("김정현", "잘지내보아요", EGender.MALE),
        Friends("세종대왕", "훈민정음", EGender.MALE),
        Friends("아이유", "푸르던", EGender.FEMALE),
        Friends("풍자", "호호", EGender.LGBT),
        Friends("박효신", "날아가~", EGender.MALE),
        Friends("김철기", "객프", EGender.MALE),
        Friends("루피", "해-삐", EGender.LGBT),
        Friends("카리나", "예쁘다", EGender.FEMALE)
    )

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recFriends.layoutManager = LinearLayoutManager(this)
        binding.recFriends.adapter = FriendsAdapter(friends)
    }
}