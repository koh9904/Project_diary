package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentFriendsBinding
import com.example.myapplication.viewmodel.UserViewModel
import com.example.object_orientedprogramming.EGender
import com.example.object_orientedprogramming.Friend
//ㅎㅇd
class FriendsFragment : Fragment(R.layout.fragment_friends) {

    val friends = arrayOf(
        Friend("유승빈", "안녕하세요", EGender.MALE),
        Friend("김세현", "반가워요", EGender.FEMALE),
        Friend("김정현", "잘지내보아요", EGender.MALE),
        Friend("세종대왕", "훈민정음", EGender.MALE),
        Friend("아이유", "푸르던", EGender.FEMALE),
        Friend("풍자", "호호", EGender.MALE),
        Friend("박효신", "날아가~", EGender.MALE),
        Friend("김철기", "객프", EGender.MALE),
        Friend("루피", "해-삐", EGender.FEMALE),
        Friend("카리나", "아름답다", EGender.FEMALE)
    )

    val viewModel: UserViewModel by viewModels()

    lateinit var binding : FragmentFriendsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFriendsBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        binding.recFriends.adapter = FriendsAdapter(friends)
        binding.recFriends.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddFriend.setOnClickListener {
            findNavController().navigate(R.id.action_friendsFragment_to_addFriendFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}