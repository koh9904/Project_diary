package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentFriendsBinding
import com.example.myapplication.repository.UserRepo
import com.example.myapplication.viewmodel.UserViewModel
class FriendsFragment : Fragment(R.layout.fragment_friends) {

    private lateinit var adapter: FriendsAdapter

    val viewModel: UserViewModel by activityViewModels()

    private var binding : FragmentFriendsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FriendsAdapter(requireContext())
        binding?.recFriends?.adapter = adapter
        binding?.recFriends?.layoutManager = LinearLayoutManager(this.activity)

        adapter.setOnItemClickListener(object : FriendsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.userName.value = adapter.getUserAt(position).userName

                findNavController().navigate(R.id.action_friendsFragment_to_friendMainFragment)
            }
        })

        binding?.btnAddFriend?.setOnClickListener {
            findNavController().navigate(R.id.action_friendsFragment_to_addFriendFragment)
        }

        observerData()
    }

    private fun observerData() {
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}