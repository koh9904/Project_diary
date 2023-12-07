package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListAddFriendsBinding
import com.example.myapplication.databinding.ListFriendsBinding

class AddFriendsAdapter(val mContext: Context) : RecyclerView.Adapter<AddFriendsAdapter.Holder>(){

    var peopleList = mutableListOf<PeopleData>()

    fun getUserAt(position: Int) : PeopleData {
        return peopleList[position]
    }

    fun setListData(data: MutableList<PeopleData>) {
        peopleList = data
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListAddFriendsBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = peopleList[position].userName
        holder.im.text = peopleList[position].userIm
    }

    override fun getItemCount() = peopleList.size

    inner class Holder(val binding: ListAddFriendsBinding) : RecyclerView.ViewHolder(binding.root) {
        val name : TextView = binding.txtName
        val im : TextView = binding.txtIm

        init {
            binding.btnAddToUser.setOnClickListener {
                val pos = absoluteAdapterPosition
                if(pos != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(pos)
                }
            }
        }
    }
}