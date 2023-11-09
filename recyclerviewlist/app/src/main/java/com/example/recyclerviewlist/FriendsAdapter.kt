package com.example.recyclerviewlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlist.databinding.ListFriendsBinding

class FriendsAdapter(val friends: Array<Friends>)
    : RecyclerView.Adapter<FriendsAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListFriendsBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount() = friends.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(friends[position])
    }

    class Holder(private val binding: ListFriendsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friends: Friends) {
            binding.imageView.setImageResource(when( friends.gender ) {
                EGender.MALE -> R.drawable.male
                EGender.FEMALE -> R.drawable.female
                EGender.LGBT -> R.drawable.lgbt
            })
            binding.txtName.text = friends.name
            binding.txtIm.text = friends.im

            binding.root.setOnClickListener {

            }
        }
    }
}