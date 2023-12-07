package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListFriendsBinding

data class FriendsAdapter(val mContext: Context) : RecyclerView.Adapter<FriendsAdapter.Holder>() {

    var userList = mutableListOf<UserData>()

    fun getUserAt(position: Int): UserData { //아이템 포지션을 리턴
        return userList[position]
    }

    fun setListData(data: MutableList<UserData>) {
        userList = data
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListFriendsBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return Holder(binding)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = userList[position].userName
        holder.im.text = userList[position].userIm
    }

    override fun getItemCount() = userList.size

    inner class Holder(binding: ListFriendsBinding) : RecyclerView.ViewHolder(binding.root) {
        val name : TextView = binding.txtName
        val im : TextView = binding.txtIm

        init {
            itemView.setOnClickListener {
                val pos = absoluteAdapterPosition
                if(pos != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(pos)
                }
            }
        }
    }



}