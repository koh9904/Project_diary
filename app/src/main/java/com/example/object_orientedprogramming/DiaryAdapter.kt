package com.example.object_orientedprogramming


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.ViewHolder> () {
        var items = ArrayList<diaryitem> ()

        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
             var image : ImageView
             var content : TextView
             var date : TextView

             init {
                 image = itemView.findViewById(R.id.image)
                 content = itemView.findViewById(R.id.content)
                 date = itemView.findViewById(R.id.date)
             }
            fun setItem(item: diaryitem) {
                val picturePath = item.image
                if (picturePath != null && picturePath != "") {
                    image.visibility = View.VISIBLE
                    image.setImageResource(R.drawable.female)
                }
                else {
                    image.visibility = View.GONE
                    image.setImageResource(R.drawable.female)
                }

                content.text = item.content
                date.text = item.date
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount() = items.size

    fun addItem (item: diaryitem) {
        items.add(item)
    }
}