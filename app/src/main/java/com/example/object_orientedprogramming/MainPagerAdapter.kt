package com.example.object_orientedprogramming

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class MainPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    /**
     * MainPagerAdapter.kt는 4개의 아이템(fragment)을 가질 것을 선언
     * **/
    override fun getItemCount(): Int = 4

    /**
     * ViewPager는 뷰를 페이지처럼 넘기면서 전활시킬 수 있도록 하는 것이라고 했는데,
     * 각 페이지에 어떤 Fragment가 들어가야할지를 정해줄 수 있습니다.
     *
     * 밑의 코드를 간단하게 보면,
     * position(Index)가 0이면 MainFragment(), 1이면 DiaryFrameFragment()를 보여줍니다.
     * **/
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MainFragment()
            1 -> DiaryFrameFragment()
            2 -> FriendFrameFragment()
            3 -> SettingFragment()
            else -> throw IllegalAccessException("Not Found Fragment Position")
        }
    }
}