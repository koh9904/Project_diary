package com.example.object_orientedprogramming.diary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.object_orientedprogramming.R
import com.example.object_orientedprogramming.diary.etc.DiaryItem

class DiaryAdapter(private val items: ArrayList<DiaryItem>, private val activity: FragmentActivity) : RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val card: CardView
        private val image: ImageView
        private val content: TextView
        private val date: TextView

        init {
            card = itemView.findViewById(R.id.card)
            image = itemView.findViewById(R.id.image)
            content = itemView.findViewById(R.id.content)
            date = itemView.findViewById(R.id.date)
        }

        fun setItem(item: DiaryItem) {
            val picturePath = item.image
            if (picturePath != "") {
                image.visibility = View.VISIBLE
                image.setImageResource(R.drawable.female)
            } else {
                image.visibility = View.GONE
                image.setImageResource(R.drawable.female)
            }

            content.text = item.content
            date.text = item.date
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)

        holder.card.setOnClickListener {
            // Log.d("DiaryListFragment", "Selected :" + item.id)
            val fragment = DiaryFragment(item)

            activity.supportFragmentManager.beginTransaction().replace(R.id.fl, fragment).commit()
        }
    }
    override fun getItemCount() = items.size
}