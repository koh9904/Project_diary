package com.example.object_orientedprogramming.diary


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.object_orientedprogramming.R
import com.example.object_orientedprogramming.diary.etc.DiaryItem
import com.example.object_orientedprogramming.diary.etc.OnDiaryItemClickListener

class DiaryAdapter(private val items: ArrayList<DiaryItem>) : RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {

        var listener: OnDiaryItemClickListener? = null
        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
             private val image : ImageView
             private val content : TextView
             private val date : TextView

             init {
                 image = itemView.findViewById(R.id.image)
                 content = itemView.findViewById(R.id.content)
                 date = itemView.findViewById(R.id.date)

                 itemView.setOnClickListener {
                     val position = bindingAdapterPosition
                     listener?.onItemClick(this, itemView, position)
                 }
             }
            fun setItem(item: DiaryItem) {
                val picturePath = item.image
                if (picturePath != "") {
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
        fun setOnItemClickListener(listener: OnDiaryItemClickListener?) {
            this.listener = listener
        }

        fun getItem(position: Int): DiaryItem {
            return items[position]
        }

        /*fun onItemClick(holder: ViewHolder, view: View, position: Int) {
            if (listener != null) {
                listener?.onItemClick(holder, view, position)
            }
            else {
                Log.d("Nothing","OnItemClick null")
            }
        }*/
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
}