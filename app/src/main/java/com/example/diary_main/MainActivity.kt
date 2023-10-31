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
            when (position) {
                0-> {
                    tab.text = "Main"
                    val drawable = resources.getDrawable(R.drawable.baseline_calendar_month_24,theme)
                    tab.icon = drawable
                }
                1-> {
                    tab.text = "Diary"
                    val drawable = resources.getDrawable(R.drawable.baseline_border_color_24,theme)
                    tab.icon = drawable
                }
                2-> {
                    tab.text = "Friend"
                    val drawable = resources.getDrawable(R.drawable.baseline_people_24,theme)
                    tab.icon = drawable
                }
                3-> {
                    tab.text = "Setting"
                    val drawable = resources.getDrawable(R.drawable.baseline_settings_24,theme)
                    tab.icon = drawable
                }
            }
        }.attach()
    }

    inner class TabAdapter : FragmentStateAdapter(supportFragmentManager,lifecycle) { //unittest
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> DiaryWrite()
                1 -> DiaryList()
                2 -> DiaryWrite()
                3 -> DiaryList()
                else -> throw IllegalStateException("Invalid Position $position")
            }
        }
    }
}

