package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListFriendsBinding
import com.example.object_orientedprogramming.EGender
import com.example.object_orientedprogramming.Friend

class FriendsAdapter(private val friends: Array<Friend>) : RecyclerView.Adapter<FriendsAdapter.Holder>() {


    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListFriendsBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(friends[position])
    }

    override fun getItemCount() = friends.size

    inner class Holder(val binding: ListFriendsBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val pos = absoluteAdapterPosition
                if(pos != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(binding.root, pos)
                }
            }
        }
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