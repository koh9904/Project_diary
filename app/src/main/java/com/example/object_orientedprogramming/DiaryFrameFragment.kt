package com.example.object_orientedprogramming

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.example.object_orientedprogramming.databinding.FragmentDiaryFrameBinding
import com.example.object_orientedprogramming.diary.DiaryFragment
import com.example.object_orientedprogramming.diary.DiaryListFragment
import com.example.object_orientedprogramming.diary.DiaryListFragment.Companion.SELECTED_DIARY
import com.example.object_orientedprogramming.diary.DiaryListFragment.Companion.SELECTED_DIARY_DATA
import com.example.object_orientedprogramming.diary.etc.DiaryItem

/**
 * DiaryFrameFragment, FriendFrameFragment를 만든이유
 * MainActivity에서 Bottom Navigation의 버튼과 ViewPager의 각 Fragment들은 1대1이여야 합니다.
 * 그러나 DiaryList에서 각 DiaryItem을 누르면 Diary 상세로 이동해야하지만, BottomNavigation은 Diary Icon이 선택된 상태로 있어야합니다.
 * 이는 즉 DiaryIcon 버튼 하나에 DiaryListFragment, DiaryFragment 2개가 매칭되니 1대1이 아니게 됩니다
 * 그래서 DiaryIcon - DiraryFrameFramgent를 매칭 시켜놓고 그 FrameFragment 안에서 DiaryListFragment, DiaryFragment를 액션에 맞게 변환시켜주고 있습니다.
 * 이는 FriendsFragment와 AddFriendFragment와 로직이 동일하기 때문에 FriendFrameFragment도 만들게 되었습니다.
 * **/

class DiaryFrameFragment: Fragment() {
    private var _binding: FragmentDiaryFrameBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Binding Error")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDiaryFrameBinding.inflate(inflater, container, false).let {
        _binding = it
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        attachDiaryListScreen()
        showDiaryDetail()
    }

    /**
     * 1. 처음에 뷰가 만들어지면 R.id.layout_diary_frame에 DiaryListFragment()를 띄워줍니다.
     * **/
    private fun attachDiaryListScreen() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.layout_diary_frame, DiaryListFragment())
            .commitAllowingStateLoss()
    }

    /**
     * 2. setFragmentResultListener()로 Fragment나 Activity로부터 전달받은 데이터를 수신할 수 있습니다.
     * DirayListFragment에서 FragmentResult로 SELECTED_DIARY 라는 key값에 데이터를 담아서 보내줍니다.
     * **/
    private fun showDiaryDetail() {
        requireActivity().supportFragmentManager.apply {
            setFragmentResultListener(SELECTED_DIARY, viewLifecycleOwner, object: FragmentResultListener {
                override fun onFragmentResult(requestKey: String, result: Bundle) {
                    val diary = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.getParcelable(SELECTED_DIARY_DATA, DiaryItem::class.java)
                    } else {
                        result.getParcelable(SELECTED_DIARY_DATA) as DiaryItem?
                    }
                    if(diary != null) {
                        val diaryDetail = DiaryFragment()
                        diaryDetail.arguments = result
                        /**
                         * 만약 DiaryListFragment에서 특정 다이어리를 클릭하였다는 정보를 보내오면,
                         * R.id.layout_diary_frame에 DiaryFragment()을 추가하여 다이어리 상세정보를 노출시켜줍니다.

                         * **/
                        beginTransaction().add(R.id.layout_diary_frame, diaryDetail)
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }
                }
            })
        }
    }
}