package com.example.diary_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.diary_main.adapter.DiaryList
import com.example.diary_main.adapter.DiaryWrite
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager = findViewById<ViewPager2>(R.id.diaryContent)
        val tabLayout = findViewById<TabLayout>(R.id.diaryTab)

        pager.adapter = TabAdapter()

        TabLayoutMediator(tabLayout, pager) {tab, position ->
            tab.text = when (position) {
                0 -> "List"
                1 -> "Write"
                else -> ""
            }
        }.attach()
    }

    inner class TabAdapter : FragmentStateAdapter(supportFragmentManager,lifecycle) { //unittest
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    print("abc")

                    DiaryList()
                }
                1 -> DiaryWrite()
                else -> throw IllegalStateException("Invalid Position $position")
            }
        }
    }
}

