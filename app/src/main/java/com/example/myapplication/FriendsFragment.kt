package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentFriendsBinding
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.viewmodel.FriendViewModel
import com.example.object_orientedprogramming.EGender
import com.example.object_orientedprogramming.Friend
class FriendsFragment : Fragment(R.layout.fragment_friends) {


    val friends = arrayOf(
        Friend("유승빈", "안녕하세요", EGender.MALE),
        Friend("김세현", "반가워요", EGender.FEMALE),
        Friend("김정현", "잘지내보아요", EGender.MALE)
    )

    private val repository = UserRepository()

    val viewModel: FriendViewModel by activityViewModels()

    lateinit var binding : FragmentFriendsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFriendsBinding.inflate(layoutInflater)

        /*
        // Inflate the layout for this fragment
        binding.recFriends.adapter = FriendsAdapter(friends)
        binding.recFriends.layoutManager = LinearLayoutManager(context)

         */

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendAdapter = FriendsAdapter(friends)
        binding.recFriends.adapter = friendAdapter
        binding.recFriends.layoutManager = LinearLayoutManager(this.activity)

        friendAdapter.setOnItemClickListener(object : FriendsAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                findNavController().navigate(R.id.action_friendsFragment_to_friendMainFragment)
            }
        })

        binding.btnAddFriend.setOnClickListener {
            findNavController().navigate(R.id.action_friendsFragment_to_addFriendFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}