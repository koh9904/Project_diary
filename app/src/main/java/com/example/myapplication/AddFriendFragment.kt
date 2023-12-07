package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentAddFriendBinding
import com.example.myapplication.viewmodel.OtherViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddFriendFragment : Fragment(R.layout.fragment_add_friend) {

    private lateinit var adapter: AddFriendsAdapter

    val viewModel: OtherViewModel by activityViewModels()

    private var binding : FragmentAddFriendBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFriendBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AddFriendsAdapter(requireContext())
        binding?.recUser?.adapter = adapter
        binding?.recUser?.layoutManager = LinearLayoutManager(this.activity)

        adapter.setOnItemClickListener(object : AddFriendsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val username = adapter.getUserAt(position).userName
                viewModel.peopleName.value = username

                viewModel.moveUserData(username, username)

                findNavController().navigate(R.id.action_addFriendFragment_to_friendsFragment)
            }
        })
        observerData()
    }

    fun observerData() {
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}