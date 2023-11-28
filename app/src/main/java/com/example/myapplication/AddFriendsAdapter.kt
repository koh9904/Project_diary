package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListFriendsBinding
import com.example.object_orientedprogramming.EGender

class AddFriendsAdapter(private val people: Array<People>) : RecyclerView.Adapter<AddFriendsAdapter.Holder>(){

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
        holder.bind(people[position])
    }

    override fun getItemCount() = people.size

    inner class Holder(val binding: ListFriendsBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val pos = absoluteAdapterPosition
                if(pos != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(binding.root, pos)
                }
            }
        }
        fun bind(people: People) {
            binding.imageView.setImageResource( when( people.gender ) {
                EGender.MALE -> R.drawable.male
                EGender.FEMALE -> R.drawable.female
            })
            binding.txtName.text = people.name
            binding.txtIm.text = people.im
        }
    }
}