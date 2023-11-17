package com.example.object_orientedprogramming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.object_orientedprogramming.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    /**
     * ViewBinding을 사용하여 xml의 컴포넌트를 쉽게 클래스에서 접근할 수 있습니다.
     * 기존에 findViewById(R.id.xxxx) 를 이용하여 각 컴포넌트들을 직접 찾아야했습니다.
     * 이 과정에서 Id값을 잘못입력하거나 타입을 잘못 선언하는 경우 에러가 발생하였는데, ViewBinding을 이용하면 이런 실수들 없이 쉽게 컴포넌트를 활용할 수 있습니다.
     * **/
    private lateinit var binding: ActivityMainBinding
    private val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        val diaryRef = database.getReference("DiaryList")
        try {
            diaryRef.child("1").let {
                it.child("diaryId").setValue(1)
                it.child("content").setValue("Diary Contents")
                it.child("date").setValue("2023-11-16")
            }
        }catch (e: Exception) {
            Log.e("error to write", "${e.message}")
        }


        setContentView(binding.root)
        setViewPagerWithBottomNav()
    }

    /**
     * 하단 Bottom Navigation과 Fragment를 연결하여 사용할 때는 ViewPager2를 사용합니다.
     * ViewPager는 말그대로 직역하여 뷰를 페이지처럼 넘기면서 전활시킬 수 있는 컴포넌트입니다.
     * activity_main.xml을 열어보면 viewpager2 와 bottom navigation이 존재하는 것을 확인할 수 있습니다.
     * **/
    private fun setViewPagerWithBottomNav() {
        /**
         * ViewPager2를 이용하기 위해서는 FragmentStateAdapter를 이용하여 각 페이지마다 어떤 Fragment를 띄워줄지 정해줄 수 있습니다.
         * MainPagerAdapter.kt 파일을 들어가보면 해당 클래스는 FragmentStateAdapter를 상속하여 만든 것을 확인 할 수 있습니다.
         * **/
        binding.viewpager.adapter = MainPagerAdapter(this@MainActivity)

        /**
         * ViewPager2에서 각 페이지를 좌우로 스와이프하여 페이지를 전환시킬 수도 있습니다.
         * 만약 MainFragment에서 FriendsFrameFragment로 스와이프하여 전환시켰을 때, Bottom Navigation도 Friends Icon이 선택되도록 변경해야합니다.
         * ViewPager2의 페이지 변경을 registerOnPageChangeCallback()을 이용하여 감지할 수 있습니다.
         * **/
        binding.viewpager.registerOnPageChangeCallback(object: OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            /**
             * ViewPgaer2의 페이지가 전환이 완료되면, onPageSelected() 함수가 호출됩니다.
             * 이 때 position값을 넘겨주는데, 이를 이용해서 BottonNavigation의 해당 Icon을 선택상태로 만들어주고 있습니다.
             * **/
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bnv.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

        })

        /**
         * 이 부분은 위와 반대로 BottomNavigation의 각 탭들을 클릭하였을 때, ViewPager2에 적절한 Fragment가 노출되도록 변경해주는 것 입니다.
         * ViewPager2에서 registerOnPageChangeCallback()을 이용하여 화면 전환을 감지하는 것처럼,
         * BottomNavigation도 버튼이 클릭된 것을 setOnItemSelectedListener()를 통해 감지할 수 있어요.
         *
         * 이 때 클릭된 item이 어떤 것인지 파악하여,
         * [binding.viewpager.currentItem = position]을 통해 ViewPager의 화면을 전환시켜줍니다.
         *
         * **/
        binding.bnv.setOnItemSelectedListener { item ->
            binding.viewpager.currentItem = when (item.itemId) {
                R.id.tab1 -> 0
                R.id.tab2 -> 1
                R.id.tab3 -> 2
                R.id.tab4 -> 3
                else -> 0
            }
            false
        }
    }
}