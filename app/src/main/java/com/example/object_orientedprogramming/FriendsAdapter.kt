package com.example.object_orientedprogramming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.object_orientedprogramming.databinding.ListFriendsBinding

class FriendsAdapter(private val friends: Array<Friend>) : RecyclerView.Adapter<FriendsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListFriendsBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(friends[position])
    }

    override fun getItemCount() = friends.size

    class Holder(private val binding: ListFriendsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend) {
            binding.imageView.setImageResource( when( friend.gender ) {
                EGender.MALE -> R.drawable.male
                EGender.FEMALE -> R.drawable.female
            })
            binding.txtName.text = friend.name
            binding.txtIm.text = friend.im
        }
    }
}