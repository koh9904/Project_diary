package com.example.object_orientedprogramming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import com.example.object_orientedprogramming.databinding.FragmentFriendFrameBinding

class FriendFrameFragment: Fragment() {
    private var _binding: FragmentFriendFrameBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Binding Error")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentFriendFrameBinding.inflate(inflater, container, false).let {
        _binding = it
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        showFriendsScreen()
        showAddFragmentScreen()
    }

    private fun showFriendsScreen() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.layout_friend_frame, FriendsFragment())
            .commitAllowingStateLoss()
    }

    private fun showAddFragmentScreen() {
        requireActivity().supportFragmentManager.apply {
            setFragmentResultListener(NAV_T0_ADD_SCREEN, viewLifecycleOwner, object: FragmentResultListener {
                override fun onFragmentResult(requestKey: String, result: Bundle) {
                    beginTransaction()
                        .add(R.id.layout_friend_frame, AddFriendFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                }
            })
        }
    }

    companion object {
        const val NAV_T0_ADD_SCREEN = "NAV_T0_ADD_SCREEN"
    }
}