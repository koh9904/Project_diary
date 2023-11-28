package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentAddFriendBinding
import com.example.object_orientedprogramming.EGender

class AddFriendFragment : Fragment() {

    private val people = arrayOf(
        People("아이유", "어른유", EGender.FEMALE),
        People("카리나", "에스파", EGender.FEMALE),
        People("페이커", "Goat", EGender.MALE),
        People("구마유시", "전어유시", EGender.MALE),
        People("고구마", "감자", EGender.FEMALE),
        People("사이다", "콜라", EGender.MALE),
        People("롯데리아", "모짜렐라인더베이컨", EGender.MALE),
        People("서브웨이", "비엘티", EGender.FEMALE),
        People("윤하", "사건의 지평선", EGender.FEMALE),
        People("폼폼이", "야옹", EGender.MALE),
        People("빼로", "야옹", EGender.MALE),
        People("겨울", "멍", EGender.MALE)
    )

    lateinit var binding : FragmentAddFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddFriendBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val peopleAdapter = AddFriendsAdapter(people)
        binding.recUser.adapter = peopleAdapter
        binding.recUser.layoutManager = LinearLayoutManager(this.activity)

        peopleAdapter.setOnItemClickListener(object : AddFriendsAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                findNavController().navigate(R.id.action_addFriendFragment_to_friendMainFragment2)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}